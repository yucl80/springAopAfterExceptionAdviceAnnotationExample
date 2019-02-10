package yucl.learn.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by YuChunlei on 2017/7/11.
 */

@Aspect
@Component
public class AutoSetterAspect {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> clazz)
    {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        ret.add(int.class);
        ret.add(long.class);
        ret.add(double.class);
        ret.add(boolean.class);
        ret.add(float.class);
        ret.add(short.class);
        ret.add(byte.class);
        ret.add(char.class);

        return ret;
    }

    private static void traversalClassField(Class clz){
        for (Field field : clz.getDeclaredFields()) {
            System.out.println(field.getName()+"  "+field.getType()+" "+ isWrapperType(field.getType()));

            AutoSetter autoSetter = field.getAnnotation(AutoSetter.class);
            if (autoSetter != null) {
                System.out.println(autoSetter.column());
            }

            traversalClassField(field.getType());
        }
    }

    /*
    Matching all methods defined in classes inside package com.howtodoinjava
within(com.howtodoinjava.*)
Matching all methods defined in classes inside package com.howtodoinjava and classes inside all sub-packages as well

For including, sub-packages use two dots.

within(com.howtodoinjava..*)
    Matching all public methods in EmployeeManager with return type EmployeeDTO and definite parameters
Use public keyword and return type in start. Also, specify all parameter types as well.
execution(public EmployeeDTO EmployeeManager.*(EmployeeDTO, Integer))
     */
    @AfterReturning(pointcut = "execution(public ClassB yucl.learn.aop.ServiceA.*(..))", returning = "result")
    public void test(JoinPoint jp, ClassB result) throws NoSuchMethodException {
        for (Field field : result.getClass().getDeclaredFields()) {
            System.out.println(field.getName()+"  "+field.getType()+" "+ isWrapperType(field.getType()));

            AutoSetter autoSetter = field.getAnnotation(AutoSetter.class);
            if (autoSetter != null) {
                System.out.println(autoSetter.column());
            }
        }

        System.out.println("call auto setter aspectj " + result.getPrdId());
    }
}



