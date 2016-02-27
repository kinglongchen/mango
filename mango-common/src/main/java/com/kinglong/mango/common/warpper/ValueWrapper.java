package com.kinglong.mango.common.warpper;

import java.io.Serializable;

/**
 * Created by chenjinlong on 16/2/24.
 */
public interface ValueWrapper extends Serializable {
    Class<?> getType();

    <T> T getValue();

}
