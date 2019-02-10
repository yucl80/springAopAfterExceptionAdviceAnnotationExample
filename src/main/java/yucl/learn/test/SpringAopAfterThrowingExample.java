package yucl.learn.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class SpringAopAfterThrowingExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        TestService testService = (TestService) context.getBean("testService");

         int rst = testService.add(10,10,"test" ,new UWDto("1234","xiaoli",new ApplyDto("521",18)));
        System.out.println(rst);


        ServiceD serviceD = (ServiceD) context.getBean("serviceDImpl");
        serviceD.test();

        try{
            Object transactionName = TransactionSynchronizationManager.getResourceMap();
            System.out.println(transactionName);
        }catch (Exception e){
            e.printStackTrace();
        }
       /* TestBean testBean = (TestBean) context.getBean("testBean");
        System.out.println("Call testBean.methodA(Hello)");
        testBean.greet("Hello");
        try {
            System.out.println("********************************");
            System.out.println("Call testBean.methodA(null)");
            testBean.greet(null);
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
        try {
            System.out.println("********************************");
            System.out.println("Call testBean.methodA('Are u there?')");
            testBean.greet("Are u there?");
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        } finally {
            context.close();
        }*/
    }
}
