package com.kinglong.mango.annotation;

import java.lang.annotation.*;

/**
 * isCreateIfNull:如果zookeeper上的节点为空的话且该当前值不为空的话,则设置zookeeper上的该节点为该值
 * isSubScribe:订单zookeeper上的节点值
 * isSyn:是否同步该值
 * isSyn=true时isCreateIfNull和isSubScribe才有效
 * <p/>
 * zookeeper上的值在非Null时优先级最高
 * Created by chenjinlong on 15/7/7.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldZkConfigurable {
    boolean isCreateIfNull() default true;

    boolean isSubScribe() default true;

    boolean isSyn() default true;
}
