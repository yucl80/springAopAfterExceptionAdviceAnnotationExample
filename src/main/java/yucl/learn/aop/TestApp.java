package yucl.learn.aop;

import org.springframework.beans.BeanUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import yucl.learn.validate.MyConstraint;
import yucl.learn.validate.UserCachedObject;
import yucl.learn.validate.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        ServiceA testService = context.getBean("serviceA",ServiceA.class);

        testService.getB("100");


        boolean bl = BeanUtils.isSimpleValueType(java.util.List.class);

        bl = BeanUtils.isSimpleValueType(java.time.Year.class);

        List<Object> lists = new ArrayList<>();
        lists.add(new UserInfo());
        lists.add(new UserInfo());

        Class<?> st = CollectionUtils.findCommonElementType(lists);

        ReflectionUtils.doWithFields(UserCachedObject.class, field -> System.out.println(field), field -> {
            MyConstraint annotation = field.getAnnotation(MyConstraint.class);
            if(annotation == null) {
                return false;
            }else {
                return true;
            }

        });



        System.out.println(st);


    }
}
