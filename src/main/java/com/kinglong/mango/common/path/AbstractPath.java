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
