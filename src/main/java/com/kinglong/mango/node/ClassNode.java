package com.kinglong.mango.node;

import com.google.common.collect.Lists;
import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.common.node.Node;
import com.kinglong.mango.zkclient.ZkConfigClient;
import org.I0Itec.zkclient.IZkDataListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by chenjinlong on 15/7/7.
 */
public class ClassNode extends ZKNode {
    private Class clazz;
    private FieldNode[] fieldNodes;

    public ClassNode(AppNode parent, Class clazz) {
        this(parent.getZkConfigClient(), parent, clazz);
    }

    public ClassNode(ZkConfigClient zkConfigClient, AppNode parent, Class clazz) {
        super(zkConfigClient, parent, clazz);
        create();
        this.clazz = clazz;
        initFields(clazz);
    }

    private void initFields(Class clazz) {
        if (clazz == null) {
            this.fieldNodes = new FieldNode[0];
            return;
        }
        Field[] fields = clazz.getFields();
        FieldNode[] fieldNodes = new FieldNode[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            FieldNode fieldNode = new FieldNode(this.getZkConfigClient(), this, field);
            fieldNodes[i] = fieldNode;
        }
        this.fieldNodes = fieldNodes;
    }

    private Annotation[] getAnnotations(FieldNode fieldNode) {
        return fieldNode.getAnnotations();
    }

    private FieldZkConfigurable getFieldZkConfigurable(FieldNode fieldNode) {
        FieldZkConfigurable fieldZkConfigurable =
                fieldNode.getAnnotation(FieldZkConfigurable.class);
        return fieldZkConfigurable;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public <T> T getValue(FieldNode fieldNode) {
        T rs;
        rs = (T) fieldNode.getValue();
        return null;
    }

    public <T> void setValue(FieldNode fieldNode, T val) {
        fieldNode.setValue(val);

    }

    public String getName() {
        return this.clazz.getName();
    }

    public Boolean isFieldZkConfigurable(FieldNode fieldNode) {
        return fieldNode.isFieldZkConfigurable();
    }

    public Boolean isCreateIfNull(FieldNode fieldNode) {
        FieldZkConfigurable fieldZkConfigurable =
                this.getFieldZkConfigurable(fieldNode);
        if (fieldZkConfigurable == null) {
            return Boolean.FALSE;
        }
        return fieldZkConfigurable.isCreateIfNull();
    }

    public Boolean isSubscribe(FieldNode fieldNode) {
        FieldZkConfigurable fieldZkConfigurable =
                this.getFieldZkConfigurable(fieldNode);
        if (fieldZkConfigurable == null) {
            return Boolean.FALSE;
        }
        return fieldZkConfigurable.isSubScribe();
    }

    public Iterator iterator() {
        List<FieldNode> fieldList = Lists.newArrayList();
        Collections.addAll(fieldList, this.fieldNodes);
        return fieldList.iterator();

    }
}
