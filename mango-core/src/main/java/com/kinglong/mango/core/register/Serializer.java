package com.kinglong.mango.core.register;

/**
 * Created by chenjinlong on 16/2/3.
 */
public interface Serializer {
    byte[] serialize(Object data);

    Object deserialize(byte[] bytes);
}
