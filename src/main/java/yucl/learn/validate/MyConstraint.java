package yucl.learn.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YuChunlei on 2017/7/9.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message() default "{javax.validation.constraints.Max.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String table() default "";

    String column() default "";


}
