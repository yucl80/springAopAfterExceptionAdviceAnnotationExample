package com.javarticles;

import com.google.gson.Gson;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by YuChunlei on 2017/6/30.
 */
@Component("transactionManager")
public class TxManager extends AbstractPlatformTransactionManager implements InitializingBean {

    @Autowired
    private TxService txService;

    public TxManager() {
        this.setNestedTransactionAllowed(false);

    }

    public TxManager(TxService txService) {
        this.setNestedTransactionAllowed(false);
        this.txService = txService;
    }


    public TxService getTxService() {
        return txService;
    }

    public void afterPropertiesSet() {
        if (this.getTxService() == null) {
            throw new IllegalArgumentException("Property 'txService' is required");
        }
    }

    private String getClientId() {
        String clientId = AopBean.getCurrentClientId();
        if (clientId != null) {
            return clientId;
        } else {
            throw new RuntimeException("can't get current client id");
           // return  null;
        }

    }

    protected boolean isExistingTransaction(Object transaction) {
        TxObject txObject = (TxObject) transaction;
        return txObject.getTxObjectHolder() != null && txObject.getTxObjectHolder().isTransactionActive();
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        TxObject txObject = (TxObject) transaction;
        TransactionSynchronizationManager.unbindResource(txObject.getTxObjectHolder().getClientId());
        txObject.getTxObjectHolder().clear();
        System.out.println("call doCleanupAfterCompletion" + new Gson().toJson(transaction));
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        TxObject txObject = new TxObject();
        TxObjectHolder conHolder = (TxObjectHolder) TransactionSynchronizationManager.getResource(this.getClientId());
        txObject.setTxObjectHolder(conHolder, false);
        System.out.println("call doGetTransaction");
        return txObject;
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        TxObject txObject = (TxObject) transaction;
        try {
            if (txObject.getTxObjectHolder() == null || txObject.getTxObjectHolder().isSynchronizedWithTransaction()) {
                String clientId = this.getClientId();
                Long txId = txService.getNewTxId(clientId);
                txObject.setTxObjectHolder(new TxObjectHolder(txId, clientId), true);
            }

            txObject.getTxObjectHolder().setSynchronizedWithTransaction(true);
            txObject.getTxObjectHolder().setTransactionActive(true);
            int timeout = this.determineTimeout(definition);
            if (timeout != -1) {
                txObject.getTxObjectHolder().setTimeoutInSeconds(timeout);
            }

            if (txObject.isNewConnectionHolder()) {
                TransactionSynchronizationManager.bindResource(this.getClientId(), txObject.getTxObjectHolder());
            }

            System.out.println("call doBegin" + new Gson().toJson(transaction));
        } catch (Exception e) {
            throw new CannotCreateTransactionException("Could not open  Connection for transaction", e);
        }
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        TxObject txObject = (TxObject) status.getTransaction();
        try {
            this.txService.commit(txObject.getTxObjectHolder().getClientId(), txObject.getTxObjectHolder().getTxId());
            System.out.println("call doCommit:" + new Gson().toJson(status));
            status.setCompleted();

        } catch (Exception e) {
            throw new TransactionSystemException("Could not commit transaction", e);
        }
    }

    @Override
    protected void doSetRollbackOnly(DefaultTransactionStatus status) throws TransactionException {
        status.setRollbackOnly();
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        TxObject txObject = (TxObject) status.getTransaction();
        try {
            this.txService.rollback(txObject.getTxObjectHolder().getClientId(), txObject.getTxObjectHolder().getTxId());
            System.out.println("call doRollback");
            //status.setRollbackOnly();

        } catch (Exception e) {
            throw new TransactionSystemException("Could not roll back  transaction", e);
        }
    }
}
