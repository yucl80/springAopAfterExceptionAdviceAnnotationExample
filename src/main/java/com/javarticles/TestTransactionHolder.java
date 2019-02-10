package com.javarticles;

import org.springframework.core.NamedThreadLocal;

/**
 * Created by YuChunlei on 2017/6/29.
 */
public class TestTransactionHolder {

    private static final ThreadLocal<TestTransactionDefinition> currentTransaction =
            new NamedThreadLocal<TestTransactionDefinition>("Current transaction");

    public static void setCurrentTransactionName(TestTransactionDefinition testTransactionDefinition) {
        currentTransaction.set(testTransactionDefinition);
    }

    public static TestTransactionDefinition getTransactionDefinition(){
        return  currentTransaction.get();
    }
}
