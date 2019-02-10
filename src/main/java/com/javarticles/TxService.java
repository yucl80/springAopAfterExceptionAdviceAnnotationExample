package com.javarticles;

/**
 * Created by YuChunlei on 2017/6/30.
 */
public interface TxService {
    long getNewTxId(String clientId);

    void commit(String clientId, long txId);

    void rollback(String clientId, long txId);
}
