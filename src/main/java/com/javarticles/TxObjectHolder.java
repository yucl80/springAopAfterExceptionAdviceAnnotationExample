package com.javarticles;

import org.springframework.transaction.support.ResourceHolderSupport;

/**
 * Created by YuChunlei on 2017/6/30.
 */
public class TxObjectHolder extends ResourceHolderSupport {
    private Long txId;
    private String clientId;
    private boolean transactionActive;
    private Boolean savepointsSupported = false;

    public TxObjectHolder(Long txId, String clientId) {
        this.clientId = clientId;
        this.txId = txId;
        this.transactionActive = false;
    }

    public boolean isTransactionActive() {
        return transactionActive;
    }

    public void setTransactionActive(boolean transactionActive) {
        this.transactionActive = transactionActive;
    }

    @Override
    public void released() {
        super.released();
    }

    @Override
    public void clear() {
        this.txId = null;
        this.clientId = null;
        this.transactionActive = false;
        super.clear();
    }


    public String getClientId() {
        return clientId;
    }

    public long getTxId() {
        return txId;
    }

    public boolean isSupportsSavepoints() {
        return this.savepointsSupported;
    }

}
