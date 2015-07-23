package com.kinglong.mango;

import com.kinglong.mango.annotation.MangoConfigurable;
import com.kinglong.mango.exception.MangoException;
import com.kinglong.mango.zkclient.ZkConfigClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by chenjinlong on 15/7/23.
 */
@Slf4j
public class Mango {
//    private static final Pattern ZOOKEEER_ADDRESS =
//            Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))" +
//                    "\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
    private ZkConfigClient zkConfigClient = null;

    public Mango(String appName, String address,List<Class> cfgClassList) {
        log.info("正在初始化配置类...");
        initMango(appName, address,cfgClassList);
        log.info("配置类初始化结束!");

    }

    private void initMango(String appName, String address,List<Class> cfgClassList) {
        this.zkConfigClient = new ZkConfigClient(address,appName);
        for (Class clazz: cfgClassList) {
            this.register(clazz);
        }
    }

    private void register(Class clazz) {
        if (this.zkConfigClient == null) {
            throw new MangoException();
        }
        this.zkConfigClient.register(clazz);
    }
}
