package com.kinglong.mango.core.register.serializer;

import com.kinglong.mango.core.common.util.ReflectUtils;
import com.kinglong.mango.core.common.util.SerializerUtil;
import com.kinglong.mango.core.register.Serializer;

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
