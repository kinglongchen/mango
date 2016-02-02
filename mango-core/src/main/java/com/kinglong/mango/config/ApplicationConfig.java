package com.kinglong.mango.config;

import com.kinglong.mango.annotation.FieldZkConfigurable;
import com.kinglong.mango.common.util.ReflectUtils;
import com.kinglong.mango.common.util.StringUtil;
import com.kinglong.mango.exception.MangoException;
import com.kinglong.mango.exception.MangoNullFieldException;
import com.kinglong.mango.node.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by chenjinlong on 16/1/9.
 */
@Slf4j
public class ApplicationConfig extends AbstractConfig{
    private static AppZkNode appNode = null;

    private RegisterConfig register;

    private String basePackage;

    @Getter
    @Setter
    private Boolean autoScan;

    public RegisterConfig getRegister() {
        if (this.register == null) {
            throw new MangoNullFieldException();
        }
        return register;
    }

    public void setRegister(RegisterConfig register) {
        this.register = register;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        log.info("[MANGO]Set basePackage with value:" + basePackage);
        this.basePackage = basePackage;
    }

    @Setter
    @Getter
    private static String name;

    public AppZkNode getAppZkNode() {
        if (appNode != null) {
            return appNode;
        }
        if (name == null) {
            throw new MangoException(null,"Application Config must indicate name!");
        }
        //初始化RootNode;
        RootZkNode rootNode = new RootZkNode();
        //初始化Mango节点
        MangoZkNode mangoNode = new MangoZkNode(rootNode);
        //初始化Config节点
        ConfigZkNode configNode = new ConfigZkNode(mangoNode);

        return new AppZkNode(configNode,name);
    }

    public void register(Class<?> cls) {
        log.debug("[MANGO] Auto Register class:"+cls.getName());
        AppZkNode appNode = getAppZkNode();
        RegisterConfig register = getRegister();
        ClassZkNode classNode = appNode.register(cls);
        classNode.synFields(register.getClient());
    }

    public void autoRegister() {
        Boolean autoScan = this.getAutoScan();
        if (!autoScan) {
            return;
        }
        String packageName = this.getBasePackage();
        if (StringUtil.isEmpty(packageName)) {
            log.warn("[MAGNO] mango auto config with null base package name!");
            return;
        }
        List<Class<?>> classList = ReflectUtils.getClasses(packageName);
        if (classList == null || classList.size() == 0) {
            return;
        }
        for (Class<?> cls : classList) {
            FieldZkConfigurable annotation = cls.getAnnotation(FieldZkConfigurable.class);
            if (annotation == null) {
                continue;
            }
            this.register(cls);
        }
    }

}
