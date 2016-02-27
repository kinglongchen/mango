package com.kinglong.mango.biz.common.exception;


import com.kinglong.mango.common.error.MangoCommonError;
import com.kinglong.mango.common.exception.MangoException;

/**
 * Created by chenjinlong on 16/2/23.
 */
public class MangoBizException extends MangoException {
    private static final long serialVersionUID = 6831092032020918849L;

    public MangoBizException() {
        this(MangoCommonError.MANGO_ERROR);
    }

    public MangoBizException(MangoCommonError error) {
        super(error);
    }
}
