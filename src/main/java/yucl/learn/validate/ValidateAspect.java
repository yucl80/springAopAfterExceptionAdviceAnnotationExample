package yucl.learn.validate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by YuChunlei on 2017/7/9.
 */

@Aspect
@Component
public class ValidateAspect {

    @Autowired
    private Validator validator;

    public void setVaidator(Validator validator) {
        this.validator = validator;
    }


    //@Before("execution(* *(@yucl.learn.validate.ValidParam (*)))")
    @Before("execution(* *(@javax.validation.Valid (*)))")
    public void valid(JoinPoint jp) throws NoSuchMethodException {
        System.out.println("call valid");
        // ConstraintViolations to return
        Set<ConstraintViolation<?>> violations = new HashSet<ConstraintViolation<?>>();

        // Get the target method
        Method interfaceMethod = ((MethodSignature) jp.getSignature()).getMethod();
        Method implementationMethod = jp.getTarget().getClass().getMethod(interfaceMethod.getName(), interfaceMethod.getParameterTypes());

        // Get the annotated parameters and validate those with the @Valid annotation
        Annotation[][] annotationParameters = implementationMethod.getParameterAnnotations();
        for (int i = 0; i < annotationParameters.length; i++) {
            Annotation[] annotations = annotationParameters[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Valid.class)) {
                    /*ValidParam valid = (ValidParam) annotation;
                    Object arg = jp.getArgs()[i];
                    violations.addAll(validator.validate(arg, valid.groups()));*/
                    violations.addAll(validator.validate(jp.getArgs()[i]));
                }
            }
        }

        // Throw an exception if ConstraintViolations are found
        System.out.println(violations);
        if (!violations.isEmpty()) {
           // throw new ConstraintViolationException(violations);
        }
    }
}

