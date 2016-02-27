package com.kinglong.mango.admin.adminenum;

import lombok.Getter;

/**
 * Created by chenjinlong on 16/2/25.
 */
public enum  MenuEnum {
    DASHBOARD("index"),

    OTHER("other");

    @Getter
    private String name;

    MenuEnum(String name) {
        this.name = name;
    }

    public static MenuEnum getMenuByName(String name) {
        MenuEnum matchMenuEnum = MenuEnum.OTHER;
        for (MenuEnum menuEnum : values()) {
            if (menuEnum.getName().endsWith(name.trim())) {
                matchMenuEnum = menuEnum;
                break;
            }
        }
        return matchMenuEnum;
    }
}
