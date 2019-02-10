package yucl.learn.aop;

import org.springframework.stereotype.Service;

/**
 * Created by YuChunlei on 2017/7/11.
 */

@Service("serviceA")
public class ServiceAImpl implements ServiceA {

    @Override
    public ClassB getB(String clientNo) {
        ClassB b = new ClassB();
        b.setPrdId("100");
        return b;
    }
}
