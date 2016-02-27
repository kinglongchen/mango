package com.kinglong.mango.biz.domian.bo;

import lombok.Data;

import java.util.Set;

/**
 * Created by chenjinlong on 16/2/25.
 */
@Data
public class StatZnodeInfoBO {
    private Set<ZnodeBO> appZnodeSet;

    private Set<ZnodeBO> classZnodeSet;

    private Set<ZnodeBO> fieldZnodeSet;

    private Set<ZnodeBO> hostZnodeSet;
}
