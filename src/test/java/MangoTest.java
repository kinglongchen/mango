import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenjinlong on 16/1/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-mango-config.xml")
public class MangoTest {
//    @Resource
//    ApplicationConfig applicationConfig;

    @Test
    public void mangoApplicationTest() {
//        AppNode appNode = applicationConfig.getAppNode();
        System.out.println("this is test");

    }
}
