package com.kinglong.mango.core.exception;

import com.kinglong.mango.core.exception.error.MangoCoreError;

/**
 * Created by chenjinlong on 15/7/17.
 */
public class MangoNullFieldException extends MangoFieldException {
    private static final long serialVersionUID = 719902149421428507L;

    public MangoNullFieldException() {
        super(MangoCoreError.MANGO_NULL_FIELD_ERROR);
    }

}
