package yucl.learn.test;

import javax.transaction.*;
import javax.transaction.xa.XAResource;

/**
 * Created by YuChunlei on 2017/7/2.
 */
public class XTransaction implements Transaction {
    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

    }

    @Override
    public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, SystemException {
        return false;
    }

    @Override
    public boolean enlistResource(XAResource xaRes) throws RollbackException, IllegalStateException, SystemException {
        return false;
    }

    @Override
    public int getStatus() throws SystemException {
        return 0;
    }

    @Override
    public void registerSynchronization(Synchronization sync) throws RollbackException, IllegalStateException, SystemException {

    }

    @Override
    public void rollback() throws IllegalStateException, SystemException {

    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {

    }
}
