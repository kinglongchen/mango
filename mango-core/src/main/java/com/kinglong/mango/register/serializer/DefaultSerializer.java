package com.kinglong.mango.register.serializer;

import com.kinglong.mango.common.util.SerializerUtil;
import com.kinglong.mango.register.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by chenjinlong on 16/2/3.
 */
@Slf4j
public class DefaultSerializer implements Serializer {
    public byte[] serialize(Object data) {
       return SerializerUtil.obj2Bytes(data);
    }

    public Object deserialize(byte[] bytes) {
        return SerializerUtil.bytes2Obj(bytes);
    }
}
