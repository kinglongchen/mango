package com.kinglong.mango.annotation;

import java.lang.annotation.*;

/**
 * Created by chenjinlong on 15/7/22.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MangoConfigurable {
}
