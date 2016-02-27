package com.kinglong.mango.core.config.spring.schema;

import com.kinglong.mango.testpack.TestClassA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenjinlong on 16/2/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-mango-config.xml")
public class MangoSchemaTest {
    @Test
    public void schemaTest() {
        while (true) {
            System.out.println(TestClassA.aa);
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
