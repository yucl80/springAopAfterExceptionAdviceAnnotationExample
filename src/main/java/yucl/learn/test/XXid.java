package yucl.learn.test;

import javax.transaction.xa.Xid;

/**
 * Created by YuChunlei on 2017/7/3.
 */
public class XXid implements Xid {
    @Override
    public int getFormatId() {
        return 0;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return new byte[0];
    }

    @Override
    public byte[] getBranchQualifier() {
        return new byte[0];
    }
}
