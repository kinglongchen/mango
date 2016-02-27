package com.kinglong.mango.common.error;

import java.io.Serializable;

/**
 * Created by chenjinlong on 16/2/23.
 */
public interface MangoError extends Serializable{

    Integer getCode();

    String getMsg();
}
