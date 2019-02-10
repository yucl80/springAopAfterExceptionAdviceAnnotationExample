package yucl.learn.validate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestValidateApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        TestValidateService testService = (TestValidateService) context.getBean("testValidateServiceImpl");

        UserCachedObject userCachedObject = new UserCachedObject();
        userCachedObject.setSex("man");
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(26);
        userInfo.setName("xiao li");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setAge(14);
        userInfo2.setName("xiao liu");

        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(userInfo2);
        userCachedObject.setUserInfoList(userInfoList);
        userCachedObject.setUserInfo(userInfo);
        testService.test(userCachedObject);

    }
}
