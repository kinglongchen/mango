package com.kinglong.mango.core.config;

import com.kinglong.mango.core.annotation.FieldZkConfigurable;
import com.kinglong.mango.core.node.AppZkNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by chenjinlong on 16/1/31.
 */
public class ApplicationConfigTest {
    ApplicationConfig applicationConfig;
    RegisterConfig registerConfig;
    @Before
    public void before() {
        applicationConfig = new ApplicationConfig();
        ApplicationConfig.setName("testAppName");

        registerConfig = new RegisterConfig();
        registerConfig.setAddress("120.26.56.174");
    }

    @Test
    public void getAppNode() {
        AppZkNode appZkNode = applicationConfig.getAppZkNode();
        assertTrue(appZkNode != null);
    }

    @Test
    public void registerTest() {
        applicationConfig.setRegister(registerConfig);
        applicationConfig.register(TestClass2.class);

    }

    @Test
    public void autoRegister() {
        applicationConfig.setRegister(registerConfig);
//        applicationConfig.autoRegister("com.kinglong.mango.testpack");
    }

}

@FieldZkConfigurable
class TestClass2{
    int a = 1;

    int b = 2;
}
