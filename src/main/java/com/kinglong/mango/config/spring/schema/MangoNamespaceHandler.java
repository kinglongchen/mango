package com.kinglong.mango.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by chenjinlong on 15/7/23.
 */
public class MangoNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("init",new MangoBeanDefinitionParser());

    }
}
