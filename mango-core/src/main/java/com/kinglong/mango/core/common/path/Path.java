package com.kinglong.mango.core.common.path;

/**
 * Created by chenjinlong on 15/7/9.
 */
public interface Path {
    String value();

    void value(String value);

    void value(Path parent, String var1);
}
