package com.kinglong.mango.node;

import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.node.DefaultZKNode;
import com.kinglong.mango.zkclient.MangoZkClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * -----------mango中的节点-----------------------zookeeper对应的节点--------------
 * FieldNode 类中字段对应的节点;<====================>/config/mango/{app.name}{class.name}/{filed.name}/
 * Created by chenjinlong on 15/7/7.
 */
@Slf4j(topic = "FieldNode")
public class FieldZkNode extends DefaultZKNode implements IZkDataListener {

    @Getter
    private Field field;

    FieldZkNode(ClassZkNode parent, Field field) {
        super(parent, field);
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
            e.printStackTrace();
        }
    }

    public void synValue(MangoZkClient mangoZkClient) {
        create(mangoZkClient);
        if (!isSyn()) {
            return;
        }

        Object val = getZkValue(mangoZkClient);
        if (val == null && isCreateIfNull() && getValue()!=null) {
            setZkValue(mangoZkClient,getValue());
        }
        if (val != null) {
            setValue(val);
        }
        if (isSubScribe()) {
            subscribeDataChanges(mangoZkClient);
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

    public void subscribeDataChanges(MangoZkClient mangoZkClient) {
        this.subscribeDataChanges(mangoZkClient, this.getPath().value());
    }

    public void subscribeDataChanges(MangoZkClient mangoZkClient, String path) {
        this.subscribeDataChanges(mangoZkClient, path, this);
    }

    public void subscribeDataChanges(MangoZkClient mangoZkClient, String path, IZkDataListener iZkDataListener) {
        log.info("[MANGO]Subscribe data change for field:"+this.field.getName());
        mangoZkClient.subscribeDataChanges(path, iZkDataListener);

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
