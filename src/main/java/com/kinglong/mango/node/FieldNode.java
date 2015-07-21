package com.kinglong.mango.node;

import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.zkclient.ZkConfigClient;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by chenjinlong on 15/7/7.
 */
@Slf4j(topic = "FieldNode")
public class FieldNode extends ZKNode implements IZkDataListener {
    private Field field;

    FieldNode(ClassNode parent, Field field) {
        this(parent.getZkConfigClient(), parent, field);
    }

    FieldNode(ZkConfigClient zkConfigClient, ClassNode parent, Field field) {
        super(zkConfigClient, parent, field);
        this.field = field;
        synValue();
    }

    public Field getField() {
        return this.field;
    }


    public Annotation[] getAnnotations() {
        if (this.field == null) {
            return new Annotation[0];
        }
        return this.field.getAnnotations();
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        if (this.field == null) {
            return null;
        }
        return this.field.getAnnotation(annotationClass);
    }

    public <K> K getValue() {
        Class clazz = ((ClassNode) this.getParent()).getClazz();
        return getValue(clazz);
    }

    public <K> K getValue(Class clazz) {
        K rs = null;
        if (this.field == null) {
            return rs;
        }

        try {
            rs = (K) this.field.get(clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public <K> void setValue(K val) {
        Class clazz = ((ClassNode) this.getParent()).getClazz();
        this.setValue(clazz, val);
    }

    public <K> void setValue(Class clazz, K val) {
        if (this.field == null) {
            return;
        }
        try {
            this.field.set(clazz, val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //TODO 重写synValue(zkConfigClient)
    public void synValue() {
        if (!isSyn()) {
            return;
        }

        if (!isExists()) {
            if (isCreateIfNull()) {
                create();
                if (getValue() != null) {
                    setZkValue(getValue());
                }
                if (isSubScribe()) {
                    subscribeDataChanges();
                }
            }
        } else {
            Object val = getZkValue();
            if (val == null) {
                if (getValue() != null) {
                    if (isCreateIfNull()) {
                        setZkValue(getValue());
                    }
                }
            } else {
                setValue(getZkValue());
            }
            if (isSubScribe()) {
                subscribeDataChanges();
            }
        }

    }

    public Boolean isFieldZkConfigurable() {
        if (this.field == null) {
            return Boolean.FALSE;
        }
        FieldZkConfigurable fieldZkConfigurable = this.getFieldZkConfigurable();
        if (fieldZkConfigurable == null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
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
        return fieldZkConfigurable;

    }

    public void subscribeDataChanges() {
        this.subscribeDataChanges(this.getZkConfigClient());
    }

    public void subscribeDataChanges(ZkConfigClient zkConfigClient) {
        this.subscribeDataChanges(this.getZkConfigClient(), this.getPath().value());
    }

    public void subscribeDataChanges(ZkConfigClient zkConfigClient, String path) {
        this.subscribeDataChanges(zkConfigClient, path, this);
    }

    public void subscribeDataChanges(ZkConfigClient zkConfigClient, String path, IZkDataListener iZkDataListener) {
        zkConfigClient.subscribeDataChanges(path, iZkDataListener);

    }

    public void handleDataChange(String s, Object o) throws Exception {
        log.info("change event : " + s + " : " + o.toString());
        setValue(this.field.getType().cast(o));
    }

    public void handleDataDeleted(String s) throws Exception {
        log.warn("delete event : " + s);

    }

    public String getName() {
        return this.field.getName();
    }
}
