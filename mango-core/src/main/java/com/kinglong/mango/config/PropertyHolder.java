package com.kinglong.mango.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by chenjinlong on 16/1/15.
 */
public class PropertyHolder {
    @Setter
    @Getter
    private Class<?> type;

    @Getter
    @Setter
    private String name;

    public PropertyHolder(Class<?> type,String name) {
        this.type = type;
        this.name = name;
    }
}
