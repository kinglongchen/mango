package com.kinglong.mango.register;

/**
 * Created by chenjinlong on 16/2/3.
 */
public interface Listener {
    void handleDataChange(String s, Object o) throws Exception;

    void handleDataDeleted(String s) throws Exception;
}
