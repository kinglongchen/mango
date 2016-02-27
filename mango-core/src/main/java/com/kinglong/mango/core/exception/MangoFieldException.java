package com.kinglong.mango.core.exception;


import com.kinglong.mango.common.exception.MangoException;
import com.kinglong.mango.core.exception.error.MangoCoreError;

/**
 * Created by chenjinlong on 15/7/17.
 */
public class MangoFieldException extends MangoException {

    private static final long serialVersionUID = -4966001136111291355L;

    public MangoFieldException() {
        this(MangoCoreError.MANGO_FIELD_ERROR);
    }

    public MangoFieldException(MangoCoreError error) {
        super(error);
    }

}
