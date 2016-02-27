package com.kinglong.mango.common.result;

import com.kinglong.mango.common.error.MangoCommonError;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by chenjinlong on 16/2/23.
 */
public class PagingResult<T> extends BaseResult{
    private static final long serialVersionUID = 94830427452089174L;

    @Getter
    @Setter
    private List<T> list;

    @Getter
    @Setter
    private int total;



    public PagingResult() {
    }

    public static <T> PagingResult<T> wrapSuccessfulResult(List<T> data, int total) {
        PagingResult<T> result = new PagingResult<T>();
        result.list = data;
        result.total = total;
        result.success = true;
        return result;
    }

    public static <T> PagingResult<T> wrapErrorResult(MangoCommonError error) {
        PagingResult<T> result = new PagingResult<T>();
        result.success = false;
        result.code = error.getCode();
        result.message = error.getMsg();
        return result;
    }

    public static <T> PagingResult<T> wrapErrorResult(Integer code, String message) {
        PagingResult<T> result = new PagingResult<T>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof PagingResult)) {
            return false;
        } else {
            PagingResult other = (PagingResult)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                List this$list = this.getList();
                List other$list = other.getList();
                if(this$list == null) {
                    if(other$list == null) {
                        return this.getTotal() == other.getTotal();
                    }
                } else if(this$list.equals(other$list)) {
                    return this.getTotal() == other.getTotal();
                }

                return false;
            }
        }
    }

    public boolean canEqual(Object other) {
        return other instanceof PagingResult;
    }

    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        List $list = this.getList();
        int result1 = result * 59 + ($list == null?0:$list.hashCode());
        result1 = result1 * 59 + this.getTotal();
        return result1;
    }

    public String toString() {
        return "PagingResult(list=" + this.getList() + ", total=" + this.getTotal() + ")";
    }
}
