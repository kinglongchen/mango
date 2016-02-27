package com.kinglong.mango.core.exception;

import com.kinglong.mango.core.exception.error.MangoCoreError;

/**
 * Created by chenjinlong on 16/1/31.
 */
public class MangoNullRegisterException extends MangoRegisterException {
    private static final long serialVersionUID = -7974209216537912942L;

    public MangoNullRegisterException() {
        super(MangoCoreError.MANGO_NULL_ZKCLIENT_ERROR);
    }

    public MangoNullRegisterException(MangoCoreError error) {
        super(error);
    }
}
