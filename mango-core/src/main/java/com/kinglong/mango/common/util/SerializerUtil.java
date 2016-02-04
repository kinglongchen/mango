package com.kinglong.mango.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by chenjinlong on 16/2/4.
 */
@Slf4j
public class SerializerUtil {
    public static <T> byte[] obj2Bytes(T obj) {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        try {
            log.info("数据序列化中。。。");
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("对象序列化失败",e);
            return null;
        }
    }

    public static <T> T bytes2Obj(byte[] bytes) {
        ObjectInputStream ois;
        ByteArrayInputStream bais;
        try {
            log.info("数据反序列化中。。。");
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return (T)ois.readObject();
        } catch (IOException e) {
            log.error("对象反序列化失败", e);
        } catch (ClassNotFoundException e) {
            log.error("对象反序列化失败", e);
        }
        return null;
    }
}
