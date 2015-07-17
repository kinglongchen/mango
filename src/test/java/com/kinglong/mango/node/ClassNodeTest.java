package com.kinglong.mango.node;

import com.kinglong.mango.zkclient.ZkConfigClient;
import junit.framework.TestCase;
import sun.security.ssl.Debug;

/**
 * Created by chenjinlong on 15/7/14.
 */
public class ClassNodeTest extends TestCase {
    ClassNode classNode;

    public void testGetClazz() throws Exception {
        Class clzz = classNode.getClazz();
        System.out.println(clzz);
        assert (classNode.getClazz()!=null);
    }

    public void testGetValue() throws Exception {

    }

    public void testSetValue() throws Exception {

    }

    public void testGetName() throws Exception {

    }

    public void testIsFieldZkConfigurable() throws Exception {

    }

    public void testIsCreateIfNull() throws Exception {

    }

    public void testIsSubscribe() throws Exception {

    }

    public void testIterator() throws Exception {

    }

    public void setUp() throws Exception {
        super.setUp();
        ZkConfigClient zkConfigClient = new ZkConfigClient("120.26.56.174:2181","testApp");
        AppNode appNode = AppNode.factory(MangoNode.factory(ConfigNode.factory(RootNode.factory(zkConfigClient))),"TestApp2");
        classNode = new ClassNode(appNode,TestClass.class);
    }
}