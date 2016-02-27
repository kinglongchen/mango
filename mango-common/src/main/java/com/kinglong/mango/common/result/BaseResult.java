package com.kinglong.mango.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenjinlong on 16/2/23.
 */
@Data
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 3861475311756746369L;
    protected boolean success;
    protected Integer code;
    protected String message;
}
