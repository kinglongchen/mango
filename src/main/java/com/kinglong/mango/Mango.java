package com.kinglong.mango;

import com.kinglong.mango.exception.MangoException;
import com.kinglong.mango.exception.error.MangoError;
import com.kinglong.mango.node.AppZkNode;
import com.kinglong.mango.zkclient.factory.MangoZkClientFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by chenjinlong on 15/7/23.
 */
@Slf4j
public class Mango {

    @Setter
    private  MangoZkClientFactory mangoZkClientFactory;



    @Getter
    private AppZkNode appNode;

    public void initZkClient(String zkAddress,
                             Integer zkPort,
                             int sessionTimeout,
                             int connectionTimeout,
                             ZkSerializer zkSerializer) {
        if (mangoZkClientFactory != null) {
            return;
        }
        mangoZkClientFactory = new MangoZkClientFactory();
        mangoZkClientFactory.setZkAddress(zkAddress);
        mangoZkClientFactory.setZkPort(zkPort);
        mangoZkClientFactory.setSessionTimeout(sessionTimeout);
        mangoZkClientFactory.setConnectionTimeout(connectionTimeout);
        mangoZkClientFactory.setZkSerializer(zkSerializer);
    }

    public void initAppNode(String appName) {

//        if (mangoZkClientFactory == null) {
//            throw new MangoException(MangoError.MANGO_NULL_ZKCLIENT_ERROR);
//        }
//        ApplicationConfig.setMangoZkClientFactory(mangoZkClientFactory);
//
//        log.info("正在初始化Mango...");
//        appNode = ApplicationConfig.factory(appName);
//        log.info("配置类初始化结束!");
    }


    public void register(Class clazz) {
        if (appNode == null) {
            throw new MangoException(MangoError.MANGO_NULL_APPNODE_ERROR);
        }
        appNode.register(clazz);
    }

    private void setMangoZkClientFactory(MangoZkClientFactory mangoZkClientFactory) {
        if (this.mangoZkClientFactory != null) {
            log.info("[MANGO]Already appoint MangoZkClientFactory");
            return;
        }
        this.mangoZkClientFactory = mangoZkClientFactory;
    }
}
