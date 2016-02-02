package com.kinglong.mango.config;

import com.kinglong.mango.zkclient.MangoZkClient;
import com.kinglong.mango.zkclient.MangoZkSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by chenjinlong on 16/1/13.
 */
@Slf4j
public class RegisterConfig extends AbstractConfig {
    //Zookeeper端口
    @Setter
    @Getter
    private Integer port = 2181;

    //Zookeeper地址
    @Setter
    @Getter
    private String address = "127.0.0.1";

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

    public synchronized MangoZkClient getClient() {
        if (mangoZkClient != null) {
            return mangoZkClient;
        }
        log.info("[MANGO]staring initialize zookeeper client...");
        String zkServers = String.format("%s:%s",address,port);
        if (zkSerializer == null) {
            zkSerializer = new MangoZkSerializer();
        }
        mangoZkClient = new MangoZkClient(zkServers,connectionTimeout,sessionTimeout,zkSerializer);
        log.info("[MANGO]Zookeeper client initialization complete!");
        return mangoZkClient;

    }
}
