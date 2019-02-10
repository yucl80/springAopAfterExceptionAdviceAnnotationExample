package yucl.learn.test;

import org.springframework.transaction.annotation.Transactional;

public class TestBean {

    @Transactional
    public String greet(String a) {
        System.out.println("methodA(" + a + ") called");
        if (a == null) {
            throw new NullPointerException();
        }
        if (!"HELLO".equals(a.toUpperCase())) {
            System.out.println("Throw NotAGreetingException");
            throw new NotAGreetingException();
        }
        return a;
    }    
}
