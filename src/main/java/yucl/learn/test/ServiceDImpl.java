package yucl.learn.test;

import com.javarticles.AopBean;
import com.javarticles.MyTansactionTemplate;
import com.javarticles.TestTransactionDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;


/**
 * Created by YuChunlei on 2017/7/2.
 */

@Service
public class ServiceDImpl implements ServiceD {
    @Autowired
    private ServiceB serviceB;

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private MyTansactionTemplate transactionTemplate;


    @Override
    public void test() {

       /* transactionTemplate.execute("1234", new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return serviceB.mult(1, 2, "1234");

            }
        });*/


        TestTransactionDefinition def = new TestTransactionDefinition();
        def.setClientId("1234");
        def.setTransactionActive(false);
        AopBean.setCurrentClientId("1234");
        // def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = null;
        try {


            txStatus = txManager.getTransaction(def);
            // execute your business logic here
            serviceB.mult(1, 2, "1234");
            txManager.commit(txStatus);
        } catch (Exception ex) {
            try {
                if (txStatus != null)
                    txManager.rollback(txStatus);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
        AopBean.setCurrentClientId(null);
    }
}
