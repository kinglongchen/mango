package com.kinglong.mango.exception;

import com.kinglong.mango.exception.error.MangoError;

/**
 * Created by chenjinlong on 15/7/17.
 */
public class MangoNullFieldException extends MangoFieldException {
    public MangoNullFieldException() {
        super(MangoError.MANGO_NULL_FIELD_ERROR);
    }

}
