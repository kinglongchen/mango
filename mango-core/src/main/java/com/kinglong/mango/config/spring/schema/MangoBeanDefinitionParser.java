package com.kinglong.mango.config.spring.schema;

import com.kinglong.mango.common.util.ReflectUtils;
import com.kinglong.mango.common.util.StringUtil;
import com.kinglong.mango.config.ApplicationConfig;
import com.kinglong.mango.config.PropertyHolder;
import com.kinglong.mango.config.RegisterConfig;
import com.kinglong.mango.exception.MangoNullRegisterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by chenjinlong on 15/7/23.
 */
@Slf4j
public class MangoBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    private final Boolean required;

    public MangoBeanDefinitionParser(Class beanClass, Boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(this.beanClass);
        beanDefinition.setLazyInit(false);

        String id = parseId(element, parserContext);
        if (!StringUtil.isEmpty(id)) {
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        }
        List<PropertyHolder> props = ReflectUtils.getProperties(beanClass);
        for (PropertyHolder prop : props) {

            String value = element.getAttribute(prop.getName());
            if (!StringUtil.isEmptyAfterTrim(value)) {
                Object reference;
                if (ReflectUtils.isPrimitive(prop.getType())) {
                    reference = value;
                } else {
                    reference = new RuntimeBeanReference(value);
                }
                beanDefinition.getPropertyValues().addPropertyValue(StringUtil.split2Camel(prop.getName()), reference);
            }
        }

        //如果ApplicationConfig中的register没有指定,则添加一个默认的register
        if (ApplicationConfig.class.equals(beanClass)
                && beanDefinition.getPropertyValues().getPropertyValue("register") == null) {
            String[] beanNames = parserContext.getRegistry().getBeanDefinitionNames();
            String defaultRegisterName = null;
            for (String beanName : beanNames) {
                BeanDefinition registerBeanDef = parserContext.getRegistry().getBeanDefinition(beanName);
                if (registerBeanDef.getBeanClassName().contains(RegisterConfig.class.getName())) {
                    defaultRegisterName = beanName;
                    break;
                }
            }
            if (defaultRegisterName == null) {
                throw new MangoNullRegisterException();
            }
            beanDefinition.getPropertyValues().
                    addPropertyValue("register",new RuntimeBeanReference(defaultRegisterName));
            beanDefinition.setInitMethodName("autoRegister");
        }


        return beanDefinition;

    }

    private String parseId(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        if (!StringUtil.isEmpty(id)) {
            if (parserContext.getRegistry().containsBeanDefinition(id)) {
                throw new IllegalStateException("Duplicate spring bean id" + id);
            }
            return id;
        }
        if (!this.required) {
            return null;
        }
        String nameProp = element.getAttribute("name");
        String interfaceProp = element.getAttribute("interface");
        if (!StringUtil.isEmpty(nameProp)) {
            id = nameProp;
        } else if (!StringUtil.isEmpty(interfaceProp)) {
            id = interfaceProp;
        } else {
            id = this.beanClass.getName();
        }
        for (int i = 2; parserContext.getRegistry().containsBeanDefinition(id + i); i++) ;
        return id;
    }


}
