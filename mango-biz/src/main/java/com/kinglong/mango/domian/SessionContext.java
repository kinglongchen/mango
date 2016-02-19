package com.kinglong.mango.domian;

import lombok.Data;

import java.util.Date;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Data
public class SessionContext {
    private String userName;

    private Date loginTime;
}
