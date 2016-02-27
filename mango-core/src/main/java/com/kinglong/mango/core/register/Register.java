package com.kinglong.mango.core.register;

/**
 * Created by chenjinlong on 16/2/3.
 */
public interface Register {
    void create(final String path);

    void createEphemeral(final String path);

    boolean exists(final String path);

    void writeData(String path,Object value);

    <T> T readData(String path);

    void subscribeDataChanges(String path,Listener listener);
}
