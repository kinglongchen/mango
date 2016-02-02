package com.kinglong.mango.node;

import junit.framework.TestCase;

/**
 * Created by chenjinlong on 15/7/14.
 */
public class ClassNodeTest extends TestCase {
    ClassZkNode classNode;

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
//        super.setUp();
//        MangoZkClient mangoZkClient = new MangoZkClient("120.26.56.174:2181","testApp");
//        AppNode appNode = AppNode.factory(MangoNode.factory(ConfigNode.factory(RootNode.factory(mangoZkClient))),"TestApp2");
//        classNode = new ClassNode(appNode,TestClass.class);
    }
}