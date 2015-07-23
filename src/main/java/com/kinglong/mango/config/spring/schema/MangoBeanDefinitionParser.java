package com.kinglong.mango.config.spring.schema;

import com.google.common.collect.Lists;
import com.kinglong.mango.Mango;
import com.kinglong.mango.zkclient.ZkConfigClient;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * Created by chenjinlong on 15/7/23.
 */
public class MangoBeanDefinitionParser implements BeanDefinitionParser {



    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String appName = element.getAttribute("app");
        String address = element.getAttribute("address");
//        ZkConfigClient zkConfigClient = new ZkConfigClient(appName,address);
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ZkConfigClient.class);
        beanDefinition.setLazyInit(false);
        ConstructorArgumentValues constructorArgumentValues =
                new ConstructorArgumentValues();
//        constructorArgumentValues.addIndexedArgumentValue(0,appName);
//        constructorArgumentValues.addIndexedArgumentValue(1, address);

        NodeList nodeList = element.getElementsByTagNameNS("http://www.kinglong.com/schema/mango","classes");
        Element classesElement = DomUtils.getChildElementsByTagName(element, "classes").get(0);
        List<Element> classElementList = DomUtils.getChildElementsByTagName(classesElement,"class");
        List<Class> classList = Lists.newArrayList();
        try {
            for (Element classElement: classElementList) {
                String className = DomUtils.getTextValue(classElement);
                classList.add(Class.forName(className));
            }
        } catch (ClassNotFoundException e) {
            //TODO log authoring
            e.printStackTrace();
        }
//        constructorArgumentValues.addIndexedArgumentValue(2,classList);
        new Mango(appName,address,classList);

        beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

        return beanDefinition;
    }

}
