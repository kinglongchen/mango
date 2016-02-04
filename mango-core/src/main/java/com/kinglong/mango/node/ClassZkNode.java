package com.kinglong.mango.node;

import com.google.common.collect.Sets;
import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.node.DefaultZKNode;
import com.kinglong.mango.common.util.ReflectUtils;
import com.kinglong.mango.register.Register;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Set;

/**-----------mango中的节点-----------------------zookeeper对应的节点--------------
 * ClassNode 类对应的节点;<=========================>/config/mango/{app.name}/{class.name}/
 * Created by chenjinlong on 15/7/7.
 */
@Slf4j
public class ClassZkNode extends DefaultZKNode {
    private Class<?> clazz;
    private Set<FieldZkNode> fieldNodeSet;

    public ClassZkNode(AppZkNode parent, Class clazz) {
        super(parent, clazz);
        this.fieldNodeSet = Sets.newHashSet();
        this.clazz = clazz;
        initFields(clazz);
    }

    private void initFields(Class clazz) {
        if (clazz == null) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && ReflectUtils.isPrimitive(field.getType())) {
                log.debug("[MANGO] Monitor the field:"+field.getName());
                FieldZkNode fieldNode = new FieldZkNode(this, field);
                this.fieldNodeSet.add(fieldNode);
            }
        }
    }

    public void synFields(Register register) {
        if (fieldNodeSet == null || fieldNodeSet.size() == 0) {
            return;
        }
        for (FieldZkNode fieldZkNode:fieldNodeSet) {
            fieldZkNode.synValue(register);
        }
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        if (this.clazz == null) {
            return null;
        }
        return this.clazz.getAnnotation(annotationClass);
    }

    public FieldZkConfigurable getFieldZkConfigurable() {
        if (this.clazz == null) {
            return null;
        }
        return this.getAnnotation(FieldZkConfigurable.class);

    }

    public Class getClazz() {
        return this.clazz;
    }

    public Iterator iterator() {
        return fieldNodeSet.iterator();
    }
}
