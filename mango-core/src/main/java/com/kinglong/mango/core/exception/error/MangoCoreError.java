package com.kinglong.mango.core.exception.error;

import com.kinglong.mango.common.error.MangoError;
import lombok.Getter;

/**
 * Created by chenjinlong on 16/1/10.
 */
public enum MangoCoreError implements MangoError{

    MANGO_FIELD_ERROR(2,"[MANGO]Filed Exception"),

    MANGO_NULL_FIELD_ERROR(21,"[MANGO]Null Filed Exception"),

    MANGO_NULL_APPNODE_ERROR(3,"[MANGO]Null AppNode Exception"),

    MANGO_ZKCLIENT_ERROR(4,"[MANGO]Mango Zookeeper Client Exception"),

    MANGO_NULL_ZKCLIENT_ERROR(41,"[MANGO]Null Mango Zookeeper Client Exception");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    MangoCoreError(Integer code, String msg) {
        this.code  = code;

        this.msg = msg;
    }
}
