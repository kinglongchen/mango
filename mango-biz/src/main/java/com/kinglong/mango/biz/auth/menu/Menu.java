package com.kinglong.mango.biz.auth.menu;

import lombok.Data;

import java.util.List;

/**
 * Created by chenjinlong on 16/2/22.
 */
@Data
public class Menu extends AbstractMenu {
    private String navIcon;

    private List<SubMenu> childes;

}
