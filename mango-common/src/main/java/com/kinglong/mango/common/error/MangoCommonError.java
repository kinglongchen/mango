package com.kinglong.mango.common.error;

import lombok.Getter;

/**
 * Created by chenjinlong on 16/1/10.
 */
public enum MangoCommonError implements MangoError{

    MANGO_SYSTEM_ERROR(0,"[MANGO]系统内部错误，请稍后再试!"),

    MANGO_ERROR(1,"[MANGO]Mango Exception");



    private final Integer code;

    private final String msg;

    MangoCommonError(Integer code, String msg) {
        this.code  = code;

        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
