package yucl.learn.test;

import com.google.gson.Gson;
import com.javarticles.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by YuChunlei on 2017/6/29.
 */

@Service
public class ServiceBImpl implements ServiceB {

    @Autowired
    ServiceC serviceC;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int mult(int a, int b, @ClientId String clientId) {
        try{
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();

            System.out.println("mul:" + new Gson().toJson(status));


        }catch (Exception e){
            e.printStackTrace();
        }
        serviceC.test("werror");
        return a*b;
    }
}
