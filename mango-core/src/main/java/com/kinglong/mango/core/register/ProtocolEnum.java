package com.kinglong.mango.core.register;

import com.kinglong.mango.common.exception.MangoException;
import lombok.Getter;

/**
 * Created by chenjinlong on 16/2/3.
 */
public enum ProtocolEnum {
    ZOOKEEPER("zookeeper");

    @Getter
    private String name;

    ProtocolEnum(String name) {
        this.name = name;
    }

    public static ProtocolEnum getByName(String name) {
        for (ProtocolEnum protocolEnum : values()) {
            if (protocolEnum.getName().equals(name)) {
                return protocolEnum;
            }
        }
        throw new MangoException(String.format("[MANGO]Mango Register Can't support the protocol with name %s",name));
    }

}
