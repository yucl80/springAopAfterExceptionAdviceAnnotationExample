package yucl.learn.aop;

import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import yucl.learn.validate.MyConstraint;
import yucl.learn.validate.UserCachedObject;
import yucl.learn.validate.UserInfo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by YuChunlei on 2017/7/13.
 */
public class TestReflection {

    private static void x(Object currObj) {
        ReflectionUtils.doWithFields(currObj.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            Object fieldValue = ReflectionUtils.getField(field, currObj);
            Class clazz = field.getType();
            if (BeanUtils.isSimpleValueType(clazz)) {
                System.out.println(field.getName() + " :  " + " isSimpleValueType" + "  " + clazz);
                if(field.getName().equalsIgnoreCase("age")){
                    ReflectionUtils.setField(field,currObj,28);
                }
            } else if (clazz.isArray()) {
                if (BeanUtils.isSimpleValueType(clazz.getComponentType())) {
                    System.out.println(field.getName() + " :  isSimpleValueType array " + clazz.getComponentType());
                } else {
                    Object[] objs = (Object[]) fieldValue;
                    for(Object obj:objs){
                        x(obj);
                        System.out.println(new Gson().toJson(obj));
                    }
                    System.out.println(field.getName() + " : array " + clazz.getComponentType());
                }
            } else if (Iterable.class.isAssignableFrom(clazz)) {
                for (Iterator<?> it = ((Iterable) fieldValue).iterator(); it.hasNext(); ) {
                    Object obj = it.next();
                    x(obj);
                }
                System.out.println(field.getName() + " :  " + "clazz.isAssignableFrom(Iterable.class)");
            } else  if(Map.class.isAssignableFrom(clazz)){
                   Map map=(Map)fieldValue;
                   map.forEach((o, o2) -> System.out.println(o+"  "+o2));
                   System.out.println(field.getName() + " :Map  " + clazz );
            }else {
                if (clazz.getPackage().getName().startsWith("java.")) {
                    System.out.println(clazz.getPackage());
                } else {
                    x(fieldValue);
                }

                System.out.println(field.getName() + " :  " + clazz + clazz.getPackage());
            }

        }, field -> {
            MyConstraint annotation = field.getAnnotation(MyConstraint.class);

            if (annotation == null) {
                return false;
            } else {
                System.out.println(annotation.column()+" "+annotation.table());
                return true;
            }

        });
        System.out.println(new Gson().toJson(currObj));
    }

    public static void main(String[] args) {
        UserCachedObject userCachedObject = new UserCachedObject();
        userCachedObject.setSex("man");
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(26);
        userInfo.setName("xiao li");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setAge(14);
        userInfo2.setName("xiao liu");

       PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(UserInfo.class, "age");
        try {
            Object obj = pd.getReadMethod().invoke(userInfo2);
            System.out.println("****"+obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // ReflectionUtils.setField();

        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(userInfo);
        userInfoList.add(userInfo2);
        userCachedObject.setUserInfoList(userInfoList);
        userCachedObject.setUserInfo(userInfo);
        x(userCachedObject);

        Map<String,UserInfo> um = new HashMap<>();
        um.put("aa",userInfo2);


    }

}
