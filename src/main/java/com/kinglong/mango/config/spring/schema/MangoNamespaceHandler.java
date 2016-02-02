package com.kinglong.mango.config.spring.schema;

import com.kinglong.mango.config.ApplicationConfig;
import com.kinglong.mango.config.RegisterConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by chenjinlong on 15/7/23.
 */
public class MangoNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("application",new MangoBeanDefinitionParser(ApplicationConfig.class,true));
        registerBeanDefinitionParser("register", new MangoBeanDefinitionParser(RegisterConfig.class,true));


    }
}
