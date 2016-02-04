package com.kinglong.mango.common.node;


import com.kinglong.mango.register.Register;

/**
 * Created by chenjinlong on 16/1/31.
 */
public interface ZkNode extends Node{

    <T> T getZkValue(Register register);

    <T> void setZkValue(Register register, T data);

    void create(Register register);

    Boolean isExists(Register register);

    ZkNode getParent();
}
