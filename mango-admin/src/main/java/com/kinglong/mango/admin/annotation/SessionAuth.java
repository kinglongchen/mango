package com.kinglong.mango.admin.annotation;

import java.lang.annotation.*;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionAuth {
}
