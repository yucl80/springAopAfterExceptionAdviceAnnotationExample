package com.javarticles;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by YuChunlei on 2017/7/2.
 */

@Service
public class MyTansactionTemplate
{
    private  TransactionTemplate transactionTemplate;

    public MyTansactionTemplate(PlatformTransactionManager transactionManager){
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public <T> T execute(String clientId ,TransactionCallback<T> action) throws TransactionException {

        AopBean.setCurrentClientId(clientId);
        T rst =  transactionTemplate.execute(action);
        AopBean.setCurrentClientId(null);
        return rst ;
    }
}
