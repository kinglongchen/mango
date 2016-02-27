package com.kinglong.mango.common.util;

import com.kinglong.mango.common.exception.MangoException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by chenjinlong on 16/2/24.
 */
@Slf4j
public class NetUtil {
    public static String LOCAL_IP_ADDRESS = null;

    private static String INVALID_IP_ADDRESS_LIST = "127.0.0.1";

    static {
        try {
            LOCAL_IP_ADDRESS = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("无法获取本机IP地址!",e);
        }
    }

    public static List<String> getAllIPAddress() {
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (java.net.SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        if (allNetInterfaces == null) {
            return new ArrayList<String>();
        }
        List<String> ipAddressList = new ArrayList<String>();
        while (allNetInterfaces.hasMoreElements())
        {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
                    .nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address)
                {
                    ipAddressList.add(ip.getHostAddress());
                }
            }
        }
        return ipAddressList;
    }

    public static String getValidNetworkAddress(){
        if (LOCAL_IP_ADDRESS != null && !INVALID_IP_ADDRESS_LIST.contains(LOCAL_IP_ADDRESS.trim())) {
            return LOCAL_IP_ADDRESS;
        }
        List<String> ipAddressList = getAllIPAddress();
        String validIpAddress = null;
        for (String ipAddress : ipAddressList) {
            if (!INVALID_IP_ADDRESS_LIST.contains(ipAddress.trim())) {
                validIpAddress = ipAddress;
                break;
            }
        }
        return validIpAddress;
    }

}
