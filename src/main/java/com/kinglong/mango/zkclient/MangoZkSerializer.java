package com.kinglong.mango.zkclient;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by chenjinlong on 15/7/7.
 */
@Slf4j
public class MangoZkSerializer implements ZkSerializer {

    private final Charset charset = Charset.forName("utf-8");

    public MangoZkSerializer() {

    }

    public byte[] serialize(Object data) throws ZkMarshallingError {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            log.info("数据序列化中。。。");
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.error("对象序列化失败",e);
            return null;
        }
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            log.info("数据反序列化中。。。");
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            log.error("对象反序列化失败", e);
        } catch (ClassNotFoundException e) {
            log.error("对象反序列化失败", e);
        }
        return null;
    }
}
