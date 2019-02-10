package com.javarticles;

import java.lang.annotation.*;

/**
 * Created by YuChunlei on 2017/7/2.
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientId {
     String value() default "";
}
