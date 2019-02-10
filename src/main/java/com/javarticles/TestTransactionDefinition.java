package com.javarticles;

import org.springframework.transaction.TransactionDefinition;

/**
 * Created by YuChunlei on 2017/6/29.
 */
public class TestTransactionDefinition implements TransactionDefinition {
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getTxId() {
        return txId;
    }

    public void setTxId(long txId) {
        this.txId = txId;
    }

    public boolean isTransactionActive() {
        return transactionActive;
    }

    public void setTransactionActive(boolean transactionActive) {
        this.transactionActive = transactionActive;
    }

    private boolean transactionActive;

    private String clientId;

    private long txId;

    @Override
    public int getPropagationBehavior() {
        return PROPAGATION_REQUIRED;
    }

    @Override
    public int getIsolationLevel() {
        return  ISOLATION_READ_COMMITTED;
    }

    @Override
    public int getTimeout() {
        return 60000;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public String getName() {
        return Thread.currentThread().getName();
    }
}
