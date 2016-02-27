package com.kinglong.mango.core.exception;

import com.kinglong.mango.common.exception.MangoException;
import com.kinglong.mango.core.exception.error.MangoCoreError;

/**
 * Created by chenjinlong on 16/1/31.
 */
public class MangoRegisterException extends MangoException {
    private static final long serialVersionUID = -6114449781012991577L;

    public MangoRegisterException() {
        super(MangoCoreError.MANGO_ZKCLIENT_ERROR);
    }

    public MangoRegisterException(MangoCoreError error) {
        super(error);
    }
}
