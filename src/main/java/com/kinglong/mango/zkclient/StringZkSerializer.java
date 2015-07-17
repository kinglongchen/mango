package com.kinglong.mango.zkclient;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.nio.charset.Charset;

/**
 * Created by chenjinlong on 15/7/7.
 */
public class StringZkSerializer implements ZkSerializer {

    private final Charset charset = Charset.forName("utf-8");

    public StringZkSerializer() {

    }

    public byte[] serialize(Object data) throws ZkMarshallingError {
        return data.toString().getBytes(this.charset);
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes, this.charset);
    }
}
