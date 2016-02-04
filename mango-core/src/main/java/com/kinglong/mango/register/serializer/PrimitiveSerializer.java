package com.kinglong.mango.register.serializer;

import com.kinglong.mango.common.util.ReflectUtils;
import com.kinglong.mango.common.util.SerializerUtil;
import com.kinglong.mango.register.Serializer;

/**
 * Created by chenjinlong on 16/2/4.
 */
public class PrimitiveSerializer implements Serializer {
    public byte[] serialize(Object data) {
        ReflectUtils.checkIsPrimitive(data.getClass());
        return SerializerUtil.obj2Bytes(data);
    }

    public Object deserialize(byte[] bytes) {
        return SerializerUtil.bytes2Obj(bytes);
    }
}
