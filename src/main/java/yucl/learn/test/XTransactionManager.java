package yucl.learn.test;

import javax.transaction.*;

/**
 * Created by YuChunlei on 2017/7/2.
 */
public class XTransactionManager implements TransactionManager {
    @Override
    public void begin() throws NotSupportedException, SystemException {

    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

    }

    @Override
    public int getStatus() throws SystemException {
        return 0;
    }

    @Override
    public Transaction getTransaction() throws SystemException {
        return null;
    }

    @Override
    public void resume(Transaction tobj) throws InvalidTransactionException, IllegalStateException, SystemException {

    }

    @Override
    public void rollback() throws IllegalStateException, SecurityException, SystemException {

    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {

    }

    @Override
    public void setTransactionTimeout(int seconds) throws SystemException {

    }

    @Override
    public Transaction suspend() throws SystemException {
        return null;
    }
}
