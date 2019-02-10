package yucl.learn.test;

import com.google.gson.Gson;
import com.javarticles.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by YuChunlei on 2017/6/29.
 */

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private  ServiceB serviceB;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int add(int a, int b, String client,@ClientId("applyDto/clientId") UWDto uwDto) {
        System.out.println("call add");

        try{
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();

            System.out.println(new Gson().toJson(status));


        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Object transactionName = TransactionSynchronizationManager.getResourceMap();
            System.out.println(transactionName);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();

            System.out.println(transactionName);
        }catch (Exception e){
            e.printStackTrace();
        }
        int val = serviceB.mult(30,10,uwDto.getApplyDto().getClientId());

        return val+b+a;
    }
}
