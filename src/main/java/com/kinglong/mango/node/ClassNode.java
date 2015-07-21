package com.kinglong.mango.node;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.exception.MangoFieldException;
import com.kinglong.mango.exception.MangoNullFieldException;
import com.kinglong.mango.zkclient.ZkConfigClient;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by chenjinlong on 15/7/7.
 */
public class ClassNode extends ZKNode {
    private Class clazz;
    private Set<String> fieldNodeSet;


    public ClassNode(AppNode parent, Class clazz) {
        this(parent.getZkConfigClient(), parent, clazz);
    }

    public ClassNode(ZkConfigClient zkConfigClient, AppNode parent, Class clazz) {
        super(zkConfigClient, parent, clazz);
        create();
        this.fieldNodeSet = Sets.newHashSet();
        this.clazz = clazz;
        initFields(clazz);
    }

    private void initFields(Class clazz) {
        if (clazz == null) {
            return;
        }
        Field[] fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            FieldNode fieldNode = new FieldNode(this, field);
            this.fieldNodeSet.add(fieldNode.getName());
        }
    }

    private void checkFieldNode(FieldNode fieldNode){
        if (fieldNode == null) {
            throw new MangoNullFieldException();
        }
        if (!fieldNodeSet.contains(fieldNode.getField().getName())) {
            throw new MangoFieldException();
        }
    }
    private Annotation[] getAnnotations(FieldNode fieldNode) {
        checkFieldNode(fieldNode);
        return fieldNode.getAnnotations();
    }

    private FieldZkConfigurable getFieldZkConfigurable(FieldNode fieldNode) {
        checkFieldNode(fieldNode);
        return fieldNode.getAnnotation(FieldZkConfigurable.class);
    }

    public Class getClazz() {
        return this.clazz;
    }

    public <T> T getValue(FieldNode fieldNode) {
        checkFieldNode(fieldNode);
        return (T) fieldNode.getValue();
    }

    public <T> void setValue(FieldNode fieldNode, T val) {
        checkFieldNode(fieldNode);
        fieldNode.setValue(val);

    }

    public String getName() {
        return this.clazz.getName();
    }

    public Boolean isFieldZkConfigurable(FieldNode fieldNode) {
        checkFieldNode(fieldNode);
        return fieldNode.isFieldZkConfigurable();
    }

    public Boolean isCreateIfNull(FieldNode fieldNode) {
        checkFieldNode(fieldNode);
        return fieldNode.isCreateIfNull();
    }

    public Boolean isSubscribe(FieldNode fieldNode) {
        checkFieldNode(fieldNode);
        return fieldNode.isSubScribe();
    }

    public Iterator iterator() {
        return fieldNodeSet.iterator();
    }
}
