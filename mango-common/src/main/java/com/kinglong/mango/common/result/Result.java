package com.kinglong.mango.common.result;

import com.kinglong.mango.common.error.MangoCommonError;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by chenjinlong on 16/2/23.
 */
public class Result<D> extends BaseResult {
    private static final long serialVersionUID = 4556773214221315699L;

    @Getter
    @Setter
    private D data;

    public Result() {

    }

    public static <D> Result<D> wrapSuccessfulResult(D data) {
        Result<D> result = new Result<D>();
        result.data = data;
        result.success = true;
        return result;
    }

    public static <D> Result<D> wrapErrorResult(MangoCommonError error) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMsg();
        return result;
    }

    public static <D> Result<D> wrapErrorResult(MangoCommonError error, Object... extendMsg) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = error.getCode();
        result.message = String.format(error.getMsg(), extendMsg);
        return result;
    }

    public static <D> Result<D> wrapErrorResult(Integer code, String message) {
        Result<D> result = new Result<D>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }
}
