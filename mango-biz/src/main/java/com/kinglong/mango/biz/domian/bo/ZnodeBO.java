package com.kinglong.mango.biz.domian.bo;

import lombok.Data;

import java.util.Set;

/**
 * Created by chenjinlong on 16/2/25.
 */
@Data
public class ZnodeBO {
    private String path;

    private String parentPath;

//    private ZnodeBO parent;
//
//    private Set<ZnodeBO> childes;

    private String name;

}
