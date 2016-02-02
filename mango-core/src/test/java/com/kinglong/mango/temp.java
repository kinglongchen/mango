package com.kinglong.mango;

import java.lang.reflect.Field;

/**
 * Created by chenjinlong on 15/7/14.
 */
public class temp {
    public String a;
    public static void main(String[] args) {
        temp v = new temp();
        Field[] fields = v.getClass().getFields();
        for (Field field:fields) {
            System.out.print(field.getName());
        }
//        System.out.println(temp.class.getName());
    }
}
