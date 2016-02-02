package com.kinglong.mango.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by chenjinlong on 15/7/3.
 */
@Slf4j
public class MangoListener  {
//    List<Class> configClass = Lists.newArrayList();
//implements ServletContextListener
//    public MangoListener() {
//        super();
//    }
//
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        log.info("正在初始化配置类...");
//        WebApplicationContext webApplicationContext =
//                WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
//        String s = servletContextEvent.getServletContext().getInitParameter("server");
//        log.info(s);
////        initMango(webApplicationContext);
//        log.info("配置类初始化完成！");
//
//    }
//
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//
//    private void initMango(ApplicationContext ac) {
//        MangoZkClient mangoZkClient = new MangoZkClient("120.26.56.174:2181","testApp");
//        Map<String,Object> configBeanMap = ac.getBeansWithAnnotation(MangoConfigurable.class);
//        for (Object obj: configBeanMap.values()) {
//            log.info("正在初始化配置类:"+obj.getClass().getName());
//            mangoZkClient.register(obj.getClass());
//        }
//    }
}
