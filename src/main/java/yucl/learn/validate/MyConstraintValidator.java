package yucl.learn.validate;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by YuChunlei on 2017/7/9.
 */


public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    private String datasource;

    private String table;

    private String column;

    @Autowired
    private TestValidateService service;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.column  = constraintAnnotation.column();

        System.out.println("call initialize"+constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("call isValid"+ value+ context);
        System.out.println(service);
        return true;
    }
}
