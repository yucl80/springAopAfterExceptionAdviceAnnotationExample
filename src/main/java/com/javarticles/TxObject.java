package com.javarticles;

import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.SmartTransactionObject;

/**
 * Created by YuChunlei on 2017/6/30.
 */
public class TxObject extends DefaultTransactionDefinition implements SmartTransactionObject {

    private boolean newTxObjectHolder;

    private TxObjectHolder txObjectHolder;

    @Override
    public boolean isRollbackOnly() {
        return false;
    }

    @Override
    public void flush() {

    }




    public TxObjectHolder getTxObjectHolder() {
        return txObjectHolder;
    }

    public void setTxObjectHolder(TxObjectHolder txObjectHolder,boolean newTxObjectHolder) {
        this.txObjectHolder = txObjectHolder;
        this.newTxObjectHolder = newTxObjectHolder;
    }

    public boolean isNewConnectionHolder() {
        return this.newTxObjectHolder;
    }

    public boolean hasTransaction() {
        return this.txObjectHolder != null ;
    }

    public void setRollbackOnly() {
        this.txObjectHolder.setRollbackOnly();
    }


}
