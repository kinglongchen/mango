package com.kinglong.mango.common.exception;

import com.kinglong.mango.common.error.MangoCommonError;
import com.kinglong.mango.common.error.MangoError;
import lombok.Getter;

/**
 * Created by chenjinlong on 15/7/17.
 */
public class MangoException extends RuntimeException {
    private static final long serialVersionUID = 7354143505395419599L;
    private static Integer DEFAULT_ERROR_CODE = 0;
    @Getter
    Integer errorCode;

    @Getter
    String errorMsg;

    public MangoException() {
        this(MangoCommonError.MANGO_ERROR);
    }

    public MangoException(MangoError err) {
        this(err.getCode(),err.getMsg());
    }

    public MangoException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;

        this.errorMsg = errorMsg;
    }

    public MangoException(String errorMsg) {
        this(DEFAULT_ERROR_CODE,errorMsg);
    }

}
