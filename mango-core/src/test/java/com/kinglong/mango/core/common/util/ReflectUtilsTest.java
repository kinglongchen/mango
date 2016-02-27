package com.kinglong.mango.core.common.util;

import com.kinglong.mango.core.config.PropertyHolder;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by chenjinlong on 16/1/14.
 */
public class ReflectUtilsTest {
    @Test
    public void getProperties() {
        for (Method method:TestClass.class.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
        for (PropertyHolder prop : ReflectUtils.getProperties(TestClass.class)) {
            System.out.println(prop.getName());
        }
    }

    @Test
    public void getClassTest() {
        List<Class<?>> classList = ReflectUtils.getClasses("com.google.common.base");
        for (Class<?> cls : classList) {
            System.out.println(cls.getName());
        }

    }
}

@Data
class TestClass {
    private Integer Aaa;

    private Integer bBb;

    private Integer ccC;

    private Integer ddd;

    private Integer EEE;

}
