package com.kinglong.mango.exception;


import com.kinglong.mango.exception.error.MangoError;

/**
 * Created by chenjinlong on 15/7/17.
 */
public class MangoFieldException extends MangoException {

    public MangoFieldException() {
        this(MangoError.MANGO_FIELD_ERROR);
    }

    public MangoFieldException(MangoError error) {
        super(error);
    }

}
