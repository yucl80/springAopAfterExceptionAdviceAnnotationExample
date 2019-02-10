package com.javarticles;

import com.google.gson.Gson;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * Created by YuChunlei on 2017/6/29.
 */

//@Component("transactionManager")
public class TestTransactionManager extends AbstractPlatformTransactionManager {

   public TestTransactionManager(){
       this.setNestedTransactionAllowed(false);
   }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        TestTransactionDefinition td=(TestTransactionDefinition)transaction;
        System.out.println("call doCleanupAfterCompletion"+ new Gson().toJson(transaction));
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        System.out.println("call doGetTransaction");
        if(TestTransactionHolder.getTransactionDefinition() == null){
            TestTransactionDefinition testTransactionDefinition = new TestTransactionDefinition();
            testTransactionDefinition.setClientId("aaaa");
            testTransactionDefinition.setTxId(100l);
            TestTransactionHolder.setCurrentTransactionName(testTransactionDefinition);
        }
        return TestTransactionHolder.getTransactionDefinition();
    }

    @Override
    protected boolean isExistingTransaction(Object transaction) throws TransactionException {
        TestTransactionDefinition td=(TestTransactionDefinition)transaction;
        return transaction != null && td.isTransactionActive();
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        TestTransactionDefinition td=(TestTransactionDefinition)transaction;
        System.out.println("call doBegin"+ new Gson().toJson(transaction));
        td.setTransactionActive(true);


    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        System.out.println("call doCommit:"+ new Gson().toJson(status));
        status.setCompleted();
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        System.out.println("call doRollback");
        status.setRollbackOnly();
    }
}
