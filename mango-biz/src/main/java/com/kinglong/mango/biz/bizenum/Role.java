package com.kinglong.mango.biz.bizenum;


import lombok.Getter;

/**
 * Created by chenjinlong on 16/2/22.
 */
public enum Role {
    ADMIN(1,"admin"),
    GUEST(2,"guest");

    @Getter
    private Integer code;

    @Getter
    private String name;

    Role(Integer code,String name) {
        this.code = code;
        this.name = name;
    }
}
