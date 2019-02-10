package com.javarticles;

import org.apache.commons.jxpath.JXPathContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

@Aspect
@Order(1)
public class AopBean {

    private static ThreadLocal<String> currentClientId = new ThreadLocal<>();

    public static String getCurrentClientId() {
        return currentClientId.get();
    }

    public static void setCurrentClientId(String clientId) {
        currentClientId.set(clientId);
    }



 /*   @AfterThrowing(value = "execution(* *.*(..))")
    public void afterAnyMethod() {
        System.out
                .println("@After AOP afterAnyMethod(), execution point: '* *.*(..)'");
    }  
 
    @AfterThrowing(value = "execution(* *.*(..))", throwing="e")
    public void afterGreeting(NotAGreetingException e) {
        System.out
                .println("@After AOP afterGreeting(), execution point: '* *.*(..)' e<" + e + ">");
    }*/

    private String getClientIdFromJoinPoint(JoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = joinPoint.getTarget().getClass().getMethod(signature.getMethod().getName(), signature.getMethod().getParameterTypes());
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            AnnotatedType[] annotatedTypes = method.getAnnotatedParameterTypes();
            int i = 0;
            for (Annotation[] annotations : parameterAnnotations) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ClientId) {
                        ClientId ann = (ClientId) annotation;
                        Object argVal = joinPoint.getArgs()[i];
                        if (ann.value().isEmpty()) {
                            return (String) argVal;
                        } else {
                            JXPathContext context = JXPathContext.newContext(argVal);
                            return (String) context.getValue(ann.value());
                        }
                    }
                }
                i++;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Before(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTx(JoinPoint joinPoint) {
        String clientId = getClientIdFromJoinPoint(joinPoint);
        if (getCurrentClientId() == null)
            setCurrentClientId(clientId);
    }

    @After(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void afterTx(JoinPoint joinPoint) {
        setCurrentClientId(null);
        try {
            Object transactionName = TransactionSynchronizationManager.getResourceMap();
            System.out.println(transactionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}