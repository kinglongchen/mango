package com.kinglong.mango.exception.error;

import lombok.Getter;

/**
 * Created by chenjinlong on 16/1/10.
 */
public enum  MangoError {

    MANGO_ERROR(1,"[MANGO]Mango Exception"),

    MANGO_FIELD_ERROR(2,"[MANGO]Filed Exception"),

    MANGO_NULL_FIELD_ERROR(21,"[MANGO]Null Filed Exception"),

    MANGO_NULL_APPNODE_ERROR(3,"[MANGO]Null AppNode Exception"),

    MANGO_ZKCLIENT_ERROR(4,"[MANGO]Mango Zookeeper Client Exception"),

    MANGO_NULL_ZKCLIENT_ERROR(41,"[MANGO]Null Mango Zookeeper Client Exception");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    MangoError(Integer code,String msg) {
        this.code  = code;

        this.msg = msg;
    }
}
