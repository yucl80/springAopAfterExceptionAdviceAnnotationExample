package yucl.learn.test;

import com.javarticles.TxManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YuChunlei on 2017/7/2.
 */
public class ServiceE {

    @Autowired
    private TxManager txManager;

    public void test(){
        txManager.getTransaction(null);
    }
}
