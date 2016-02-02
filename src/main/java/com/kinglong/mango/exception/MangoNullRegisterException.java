package com.kinglong.mango.exception;

import com.kinglong.mango.exception.error.MangoError;

/**
 * Created by chenjinlong on 16/1/31.
 */
public class MangoNullRegisterException extends MangoRegisterException {
    public MangoNullRegisterException() {
        super(MangoError.MANGO_NULL_ZKCLIENT_ERROR);
    }

    public MangoNullRegisterException(MangoError error) {
        super(error);
    }
}
