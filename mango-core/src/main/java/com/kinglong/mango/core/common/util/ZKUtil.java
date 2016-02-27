package com.kinglong.mango.core.common.util;

/**
 * Created by chenjinlong on 15/7/7.
 */
public class ZKUtil {
    public static String makePath(String parent, String nodeName) {
        return parent + "/" + nodeName;
    }
}
