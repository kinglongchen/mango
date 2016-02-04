package com.kinglong.mango.common.util;

import com.google.common.collect.Lists;
import com.kinglong.mango.config.PropertyHolder;
import com.kinglong.mango.exception.MangoException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by chenjinlong on 16/1/14.
 */
public class ReflectUtils {
    public final static String GETTER_METHOD_PREFIX = "get";
    public final static String SETTER_METHOD_PREFIX = "set";
    public final static String ISSETTER_METHOD_PREFIX = "is";

    public static Boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() ||cls == Boolean.class || cls == Byte.class
                || cls == Character.class || cls == Short.class || cls == Integer.class
                || cls == Long.class || cls == Float.class || cls == Double.class
                || cls == String.class || cls == Date.class || cls == Class.class;
    }

    public static void checkIsPrimitive(Class<?> cls) {
        if (!isPrimitive(cls)) {
            throw new MangoException("[MANGO]Non Primitive Type:"+cls.getName());
        }
    }

    public static List<Method> getSetterMethods(Class<?> cls) {
        return getSpecificMethods(cls, SETTER_METHOD_PREFIX, 1);
    }

    public static List<Method> getGetterMethods(Class<?> cls) {
        return getSpecificMethods(cls, GETTER_METHOD_PREFIX, 0);
    }


    public static Boolean isSetterMethod(Method method) {
        return isSpecificMethod(method, SETTER_METHOD_PREFIX, 1);
    }

    public static Boolean isGetterMethod(Method method) {
        return isSpecificMethod(method, GETTER_METHOD_PREFIX, 0);
    }

    public static List<PropertyHolder> getProperties(Class<?> cls) {
        List<PropertyHolder> props = Lists.newArrayList();
        List<Method> setterMethods = getSetterMethods(cls);

        for (Method method : setterMethods) {
            String prop = getSetterPropertyName(method);
            Class<?> paramType = method.getParameterTypes()[0];
            Method getOrIsMethod = null;
            try {
                getOrIsMethod = cls.getMethod(GETTER_METHOD_PREFIX + prop);
            } catch (NoSuchMethodException e) {
                try {
                    getOrIsMethod = cls.getMethod(ISSETTER_METHOD_PREFIX+ prop);
                } catch (NoSuchMethodException e1) {
                }
            }
            if (getOrIsMethod != null && paramType.equals(getOrIsMethod.getReturnType())) {
                props.add(new PropertyHolder(paramType,StringUtil.camel2Split(prop)));
            }

        }
        return props;
    }

    public static String getSetterPropertyName(Method method) {
        if (!isSetterMethod(method)) {
            return null;
        }

        return method.getName().substring(3);
    }

    /**
     * 获取{@param cls}中获取指定的方法
     *
     * @param cls
     * @param methodPrefix
     * @param paramLength
     * @return
     */
    public static List<Method> getSpecificMethods(Class<?> cls, String methodPrefix, Integer paramLength) {
        Method[] methods = cls.getMethods();
        List<Method> specificMethods = Lists.newArrayList();
        for (Method method : methods) {
            if (isSpecificMethod(method, methodPrefix, paramLength)) {
                specificMethods.add(method);
            }
        }
        return specificMethods;
    }

    /**
     * 判断是否是指定的方法，该方法如果是{@param methodPrefix} 开头,参数为{@param paramLength}个,且方法为public的则返回true
     *
     * @param method       进行判断的Method对象
     * @param methodPrefix 指定的方法前缀
     * @param paramLength  指定的方法参数长度
     * @return
     */
    public static Boolean isSpecificMethod(Method method, String methodPrefix, Integer paramLength) {
        if (method == null) {
            return false;
        }
        if (StringUtil.isEmpty(methodPrefix)) {
            return true;
        }
        String methodName = method.getName();
        return methodName.length() > methodPrefix.length()
                && methodName.startsWith(methodPrefix)
                && Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length == paramLength;
    }


    /**
     * 从包package中获取所有的Class
     * @param packageName
     * @return
     */
    public static List<Class<?>> getClasses(String packageName){

        //第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //是否循环迭代
        boolean recursive = true;
        //获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //循环迭代下去
            while (dirs.hasMoreElements()){
                //获取下一个元素
                URL url = dirs.nextElement();
                //得到协议的名称
                String protocol = url.getProtocol();
                //如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    //以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)){
                    //如果是jar包文件
                    //定义一个JarFile
                    JarFile jar;
                    try {
                        //获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        //从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        //同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            //如果是以/开头的
                            if (name.charAt(0) == '/') {
                                //获取后面的字符串
                                name = name.substring(1);
                            }
                            //如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                //如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    //获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                //如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive){
                                    //如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        //去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            //添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(),
                        recursive,
                        classes);
            }
            else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
