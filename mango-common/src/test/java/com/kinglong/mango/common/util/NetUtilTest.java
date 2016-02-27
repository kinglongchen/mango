package com.kinglong.mango.common.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by chenjinlong on 16/2/24.
 */
public class NetUtilTest {

    @Test
    public void testGetAllIPAddress() throws Exception {
        List<String> ipList = NetUtil.getAllIPAddress();
        for (String ip : ipList) {
            System.out.println(ip);
        }
    }

    @Test
    public void testGetValidNetworkAddress() throws Exception {
        System.out.println(NetUtil.getValidNetworkAddress());
    }
}