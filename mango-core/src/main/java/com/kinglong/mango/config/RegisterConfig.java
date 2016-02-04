package com.kinglong.mango.config;

import com.kinglong.mango.exception.MangoException;
import com.kinglong.mango.register.ProtocolEnum;
import com.kinglong.mango.register.Register;
import com.kinglong.mango.register.Serializer;
import com.kinglong.mango.register.zookeeper.ZkRegister;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
    private Serializer serializer = null;

    @Getter
    private Register register = null;

    @Getter
    private ProtocolEnum protocol = ProtocolEnum.ZOOKEEPER;

    public void setProtocol(String protocol) {
        this.protocol = ProtocolEnum.getByName(protocol);
    }

    public synchronized Register getClient() {
        if (register != null) {
            return register;
        }
        log.info("[MANGO]staring initialize zookeeper client...");

        switch (protocol) {
            case ZOOKEEPER:
                register = initZkRegister();
                break;
            default:
                throw new MangoException("[MANGO]Unsupported protocol type:"+protocol.getName());
        }

        log.info("[MANGO]Zookeeper client initialization complete!");
        return register;

    }

    private Register initZkRegister() {
        if (this.serializer == null) {
            return new ZkRegister(address,port,connectionTimeout,sessionTimeout);
        }
        return new ZkRegister(address,port,connectionTimeout,sessionTimeout,this.serializer);
    }


}
