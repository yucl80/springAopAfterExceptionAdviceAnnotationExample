package com.javarticles;

import org.springframework.stereotype.Service;

/**
 * Created by YuChunlei on 2017/7/1.
 */

@Service
public class TxServiceImpl implements TxService {
    @Override
    public long getNewTxId(String clientId) {
        return (int) (Math.random() * 100000);
    }

    @Override
    public void commit(String clientId, long txId) {

    }

    @Override
    public void rollback(String clientId, long txId) {

    }
}
