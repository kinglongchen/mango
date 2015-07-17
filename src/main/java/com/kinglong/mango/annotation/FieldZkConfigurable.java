package com.kinglong.mango.annotation;

import java.lang.annotation.*;

/**
 * Created by chenjinlong on 15/7/7.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldZkConfigurable {
    @Deprecated boolean isCreateIfNull() default true;

    boolean isSubScribe() default true;

    boolean isSyn() default true;
}
