package com.kinglong.mango.biz.auth;

import com.kinglong.mango.biz.auth.menu.Menu;
import com.kinglong.mango.biz.auth.menu.SubMenu;
import com.kinglong.mango.biz.bizenum.Role;

import java.util.*;

/**
 * Created by chenjinlong on 16/2/22.
 */
public class RoleAuth {
    public static final Map<Role, List<Menu>> roleAuthList = new HashMap<Role, List<Menu>>();

    static {

        List<SubMenu> childes;

        //Start Dashboard Menu Config
        Menu dashboard = new Menu();
        dashboard.setName("Dashboard");
        dashboard.setNavIcon("fa-dashboard");
        dashboard.setSort(0);
        dashboard.setHrefUrl("/mango/index");
        //End Dashboard Menu Config

        //Start layouts Menu Config
        Menu layouts = new Menu();
        layouts.setName("Layouts");
        layouts.setNavIcon("fa-laptop");
        layouts.setSort(1);

        //Start Sub Grid System Menu Config
        SubMenu gridSystem = new SubMenu();

        gridSystem.setName("Grid System");
        gridSystem.setHrefUrl("/mango/dashboard");
        gridSystem.setSort(0);
        //End Sub Grid System Menu Config


        childes = new ArrayList<SubMenu>();
        childes.add(gridSystem);
        layouts.setChildes(childes);
        //End layouts Menu Config

        List<Menu> adminMenus = new ArrayList<Menu>();
        adminMenus.add(dashboard);
        adminMenus.add(layouts);

        roleAuthList.put(Role.ADMIN, adminMenus);
    }

    public static List<Menu> getMenusByRole(Role role) {
        List<Menu> menus = roleAuthList.get(role);
        if (menus == null) {
            menus = new ArrayList<Menu>();
        }
        Collections.sort(menus, new Comparator<Menu>() {
            public int compare(Menu o1, Menu o2) {
                return o1.getSort()-o2.getSort();
            }
        });
        for (Menu menu :menus) {
            Collections.sort(menus, new Comparator<Menu>() {
                public int compare(Menu o1, Menu o2) {
                    return o1.getSort()-o2.getSort();
                }
            });
        }
        return menus;
    }
}
