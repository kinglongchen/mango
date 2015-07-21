package com.kinglong.mango.zkclient;

import com.google.common.collect.Maps;
import com.kinglong.mango.node.*;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.util.Map;

/**
 * Created by chenjinlong on 15/7/7.
 */
public class ZkConfigClient extends ZkClient {

    private Map<String, ClassNode> cacheMap = Maps.newHashMap();

    private AppNode appNode = null;

    public ZkConfigClient(String zkServers, String appName) {
        this(zkServers, 5000, appName);

    }

    public ZkConfigClient(String zkServers,
                          int connectionTimeout,
                          String appName) {
        this(zkServers, connectionTimeout, 5000, appName);
    }

    public ZkConfigClient(String zkServers, int sessionTimeout, int connectionTimeout, String appName) {
        this(zkServers, sessionTimeout, connectionTimeout, new MangoZkSerializer(), appName);
    }

    public ZkConfigClient(String zkServers,
                          int sessionTimeout,
                          int connectionTimeout,
                          ZkSerializer zkSerializer,
                          String appName) {
        super(zkServers, sessionTimeout, connectionTimeout, zkSerializer);
        initAppZookeeper(appName);
    }

    private void initAppZookeeper(String appName) {
        this.appNode = AppNode.factory(MangoNode.factory(ConfigNode.factory(RootNode.factory(this))), appName);
    }

    public void register(Class clazz) {
        if (!cacheMap.containsKey(clazz.getName())) {
            ClassNode classNode = new ClassNode(appNode, clazz);
            cacheMap.put(classNode.getName(), classNode);
        }
    }

}
