package com.kinglong.mango.node;

import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.node.DefaultZKNode;
import com.kinglong.mango.common.util.ReflectUtils;
import com.kinglong.mango.exception.MangoException;
import com.kinglong.mango.register.Listener;
import com.kinglong.mango.register.Register;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * -----------mango中的节点-----------------------zookeeper对应的节点--------------
 * FieldNode 类中字段对应的节点;<====================>/config/mango/{app.name}{class.name}/{filed.name}/
 * Created by chenjinlong on 15/7/7.
 */
@Slf4j(topic = "FieldNode")
public class FieldZkNode extends DefaultZKNode implements Listener {

    @Getter
    private Field field;

    FieldZkNode(ClassZkNode parent, Field field) {
        super(parent, field);
        if (!ReflectUtils.isPrimitive(field.getType())) {
            throw new MangoException("[MANGO] Unsupported primitive filed: "+field.getType().getName());
        }
        this.field = field;
    }

    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        if (this.field == null) {
            return null;
        }
        return this.field.getAnnotation(annotationClass);
    }

    public <K> K getValue() {
        Class clazz = ((ClassZkNode) this.getParent()).getClazz();
        return getValue(clazz);
    }

    public <K> K getValue(Class clazz) {
        K rs = null;
        if (this.field == null) {
            return null;
        }

        try {
            rs = (K) this.field.get(clazz);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e);
        }
        return rs;
    }

    public <K> void setValue(K val) {
        Class clazz = ((ClassZkNode) this.getParent()).getClazz();
        this.setValue(clazz, val);
    }

    public <K> void setValue(Class clazz, K val) {
        if (this.field == null) {
            return;
        }
        try {
            this.field.set(clazz, val);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e);
        }
    }

    public void synValue(Register register) {
        create(register);
        if (!isSyn()) {
            return;
        }

        Object val = getZkValue(register);
        if (val == null && isCreateIfNull() && getValue()!=null) {
            setZkValue(register,getValue());
        }
        if (val != null) {
            setValue(val);
        }
        if (isSubScribe()) {
            subscribeDataChanges(register);
        }
    }

    public Boolean isCreateIfNull() {
        if (this.field == null) {
            return Boolean.FALSE;
        }
        FieldZkConfigurable fieldZkConfigurable = this.getFieldZkConfigurable();
        if (fieldZkConfigurable == null) {
            return Boolean.FALSE;
        }
        if (fieldZkConfigurable.isCreateIfNull()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isSyn() {
        if (this.field == null) {
            return Boolean.FALSE;
        }
        FieldZkConfigurable fieldZkConfigurable = this.getFieldZkConfigurable();
        if (fieldZkConfigurable == null) {
            return Boolean.FALSE;
        }
        if (fieldZkConfigurable.isSyn()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isSubScribe() {
        if (this.field == null) {
            return Boolean.FALSE;
        }
        FieldZkConfigurable fieldZkConfigurable = this.getFieldZkConfigurable();
        if (fieldZkConfigurable == null) {
            return Boolean.FALSE;
        }
        if (fieldZkConfigurable.isSubScribe()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public FieldZkConfigurable getFieldZkConfigurable() {
        if (this.field == null) {
            return null;
        }
        FieldZkConfigurable fieldZkConfigurable =
                this.getAnnotation(FieldZkConfigurable.class);
        if (fieldZkConfigurable == null) {
            fieldZkConfigurable = ((ClassZkNode)this.getParent()).getFieldZkConfigurable();
        }
        return fieldZkConfigurable;

    }

    public void subscribeDataChanges(Register register) {
        this.subscribeDataChanges(register, this.getPath().value());
    }

    public void subscribeDataChanges(Register register, String path) {
        this.subscribeDataChanges(register, path, this);
    }

    public void subscribeDataChanges(Register register, String path, Listener listener) {
        log.info("[MANGO]Subscribe data change for field:"+this.field.getName());
        register.subscribeDataChanges(path, listener);

    }

    public void handleDataChange(String s, Object o) throws Exception {
        log.info("[MANGO]change event : " + s + " : " + o.toString());
        setValue(this.field.getType().cast(o));
    }

    public void handleDataDeleted(String s) throws Exception {
        log.warn("[MANGO]delete event : " + s);

    }

    public String getName() {
        return this.field.getName();
    }
}
