package com.kinglong.mango.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by chenjinlong on 15/7/7.
 */
public class MangoZkClient extends ZkClient {

    private final static Integer ZK_DEFAULT_CONNECTION_TIMEOUT = 5000;

    private final static Integer ZK_DEFAULT_SESSION_TIMEOUT = 5000;


    public MangoZkClient(String zkServers) {
        this(zkServers,ZK_DEFAULT_CONNECTION_TIMEOUT);
    }

    public MangoZkClient(String zkServers,
                         int connectionTimeout) {
        this(zkServers, connectionTimeout, ZK_DEFAULT_SESSION_TIMEOUT);
    }

    public MangoZkClient(String zkServers, int sessionTimeout, int connectionTimeout) {
        this(zkServers, sessionTimeout, connectionTimeout, new MangoZkSerializer());
    }

    public MangoZkClient(String zkServers,
                         int sessionTimeout,
                         int connectionTimeout,
                         ZkSerializer zkSerializer) {
        super(zkServers, sessionTimeout, connectionTimeout, zkSerializer);
    }

}
