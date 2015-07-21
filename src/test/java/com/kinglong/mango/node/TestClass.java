package com.kinglong.mango.node;

import com.kinglong.mango.annotation.FieldZkConfigurable;

import java.io.Serializable;

/**
 * Created by chenjinlong on 15/7/13.
 */
public class TestClass implements Serializable{
//    private static final long serialVersionUID = -5182532647273106745L;
    @FieldZkConfigurable
    public static subTestClass var5=new subTestClass("2000000");


}
