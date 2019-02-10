package yucl.learn.test;

import org.springframework.stereotype.Service;

/**
 * Created by YuChunlei on 2017/7/1.
 */
@Service
public class ServiceCImpl implements  ServiceC {

    @Override
    public String test(String x) {
        if(x.equalsIgnoreCase("error")){
            throw new RuntimeException("test");
        }
        return x;
    }
}
