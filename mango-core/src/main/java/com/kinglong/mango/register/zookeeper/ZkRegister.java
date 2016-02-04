package com.kinglong.mango.register.zookeeper;

import com.kinglong.mango.register.Register;
import com.kinglong.mango.register.Listener;
import com.kinglong.mango.register.Serializer;
import com.kinglong.mango.register.serializer.DefaultSerializer;
import com.kinglong.mango.register.serializer.PrimitiveSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by chenjinlong on 16/2/3.
 */
public class ZkRegister implements Register {

    private ZkClient zkClient;

    private final static Integer ZK_DEFAULT_CONNECTION_TIMEOUT = 5000;

    private final static Integer ZK_DEFAULT_SESSION_TIMEOUT = 5000;

    public ZkRegister(String address,
                      int port) {
        this(address,port, ZK_DEFAULT_CONNECTION_TIMEOUT);
    }

    public ZkRegister(String address,
                      int port,
                      int connectionTimeout) {
        this(address, port, connectionTimeout, ZK_DEFAULT_SESSION_TIMEOUT);
    }

    public ZkRegister(String address,
                      int port,
                      int sessionTimeout,
                      int connectionTimeout) {
        this(address,port, sessionTimeout, connectionTimeout, new PrimitiveSerializer());
    }

    public ZkRegister(String address,
                      int port,
                      int sessionTimeout,
                      int connectionTimeout,
                      final Serializer zkSerializer) {
        String zkServers = String.format("%s:%s",address,port);
        this.zkClient = new ZkClient(zkServers, sessionTimeout, connectionTimeout, new ZkSerializer() {
            public byte[] serialize(Object o) throws ZkMarshallingError {
                return zkSerializer.serialize(o);
            }

            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                return zkSerializer.deserialize(bytes);
            }
        });
    }

    public void create(String path) {
        this.zkClient.createPersistent(path);
    }

    public boolean exists(String path) {
        return this.zkClient.exists(path);
    }

    public void writeData(String path, Object value) {
        this.zkClient.writeData(path, value);
    }

    public <T> T readData(String path) {
        return this.zkClient.readData(path);
    }

    public void subscribeDataChanges(String path, final Listener listener) {
        this.zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                listener.handleDataChange(s, o);
            }

            public void handleDataDeleted(String s) throws Exception {
                listener.handleDataDeleted(s);
            }
        });
    }
}
