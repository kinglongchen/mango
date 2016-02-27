package com.kinglong.mango.biz.domian;

import com.kinglong.mango.biz.bizenum.Role;
import lombok.Data;

import java.util.Date;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Data
public class SessionContext {
    private String userName;

    private String sessionId;

    private Role role;

    private Date loginTime;

    public Role getRole() {
        if (role == null) {
            return Role.GUEST;
        }
        return role;
    }
}
