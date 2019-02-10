package com.javarticles;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * Created by YuChunlei on 2017/6/29.
 */
public class TestTransactionStatus implements TransactionStatus {

    @Override
    public boolean isNewTransaction() {
        return false;
    }

    @Override
    public boolean hasSavepoint() {
        return false;
    }

    @Override
    public void setRollbackOnly() {

    }

    @Override
    public boolean isRollbackOnly() {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public Object createSavepoint() throws TransactionException {
        return null;
    }

    @Override
    public void rollbackToSavepoint(Object savepoint) throws TransactionException {

    }

    @Override
    public void releaseSavepoint(Object savepoint) throws TransactionException {

    }
}
