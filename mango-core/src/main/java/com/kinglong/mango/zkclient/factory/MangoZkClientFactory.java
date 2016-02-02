package com.kinglong.mango.zkclient.factory;

import com.kinglong.mango.zkclient.MangoZkClient;
import com.kinglong.mango.zkclient.MangoZkSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by chenjinlong on 16/1/9.
 */

@Slf4j
public class MangoZkClientFactory {
    //Zookeeper端口
    @Setter
    @Getter
    private Integer zkPort = 2181;

    //Zookeeper地址
    @Setter
    @Getter
    private String zkAddress = "127.0.0.1";

    //Zookeeper session超时时间
    @Setter
    @Getter
    private Integer sessionTimeout = 5000;

    //Zookeeper session超时时间
    @Setter
    @Getter
    private Integer connectionTimeout = 5000;

    //Zookeeper 序列化对象
    @Setter
    @Getter
    private ZkSerializer zkSerializer = null;

    @Getter
    private MangoZkClient mangoZkClient = null;

    public MangoZkClientFactory() {}

    public MangoZkClientFactory(String zkAddress,Integer zkPort) {
        this.zkAddress = zkAddress;
        this.zkPort = zkPort;
    }

    public synchronized MangoZkClient getClient() {
        if (mangoZkClient != null) {
            return mangoZkClient;
        }
        log.info("[MANGO]staring initialize zookeeper client...");
        String zkServers = String.format("%s:%s",zkAddress,zkPort);
        if (zkSerializer == null) {
            zkSerializer = new MangoZkSerializer();
        }
        mangoZkClient = new MangoZkClient(zkServers,connectionTimeout,sessionTimeout,zkSerializer);
        log.info("[MANGO]Zookeeper client initialization complete!");
        return mangoZkClient;

    }

}
