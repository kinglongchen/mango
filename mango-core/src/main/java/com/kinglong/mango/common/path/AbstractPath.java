package com.kinglong.mango.common.path;

/**
 * Created by chenjinlong on 15/7/9.
 */
public abstract class AbstractPath implements Path {
    private String value;
    private static String ROOTPATH = "/";

    public String value() {
        return value.trim();
    }

    public void value(String value) {
        this.value = value.trim();
    }

    /**
     * 设置一个路径有以下情况:
     * (1).如果parent为空的话，那么该节点的路径为value值；
     * (2).如果parent不为空的话，那么有如下情况:
     *      1).如果parent为"/"的话,那么该节点为/{value}
     *      2).如果parent不为"/"的话,那么该节点为{parent}/{value}/
     * @param parent 父节点路径
     * @param value parent节下子节点的名字
     */
    public void value(Path parent, String value) {
        value = value.trim();
        if (parent == null) {
            this.value(value);
        } else {
            if (parent.value().equals(ROOTPATH)) {
                this.value(ROOTPATH + value);
            } else {
                this.value(parent.value() + "/" + value);
            }
        }
    }
}
