package com.kinglong.mango.register;

import org.I0Itec.zkclient.exception.ZkMarshallingError;

/**
 * Created by chenjinlong on 16/2/3.
 */
public interface Serializer {
    byte[] serialize(Object data);

    Object deserialize(byte[] bytes);
}
