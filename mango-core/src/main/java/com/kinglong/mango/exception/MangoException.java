package com.kinglong.mango.exception;

import com.kinglong.mango.exception.error.MangoError;
import lombok.Getter;

/**
 * Created by chenjinlong on 15/7/17.
 */
public class MangoException extends RuntimeException {
    private static Integer DEFAULT_ERROR_CODE = 0;
    @Getter
    Integer errorCode;

    @Getter
    String errorMsg;

    public MangoException() {
        this(MangoError.MANGO_ERROR);
    }

    public MangoException(MangoError err) {
        this(err.getCode(),err.getMsg());
    }

    public MangoException(Integer errorCode,String errorMsg) {
        this.errorCode = errorCode;

        this.errorMsg = errorMsg;
    }

    public MangoException(String errorMsg) {
        this(DEFAULT_ERROR_CODE,errorMsg);
    }

}
