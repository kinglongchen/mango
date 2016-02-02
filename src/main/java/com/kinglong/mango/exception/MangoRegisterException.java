package com.kinglong.mango.exception;

import com.kinglong.mango.exception.error.MangoError;

/**
 * Created by chenjinlong on 16/1/31.
 */
public class MangoRegisterException extends MangoException {
    public MangoRegisterException() {
        super(MangoError.MANGO_ZKCLIENT_ERROR);
    }

    public MangoRegisterException(MangoError error) {
        super(error);
    }
}
