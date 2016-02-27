package com.kinglong.mango.biz.auth.menu;

import lombok.Data;

/**
 * Created by chenjinlong on 16/2/22.
 */
@Data
public abstract class AbstractMenu {
    private int sort;

    private String name;

    private String hrefUrl = "#";

}
