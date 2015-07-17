import com.kinglong.mango.node.TestClass;
import com.kinglong.mango.zkclient.ZkConfigClient;

/**
 * Created by chenjinlong on 15/7/9.
 */
public class test {
    public static void main(String[] args) {
        ZkConfigClient zkConfigClient = new ZkConfigClient("120.26.56.174:2181","testApp");
        zkConfigClient.register(TestClass.class);

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("var2=");
            System.out.println(TestClass.var3);
        }
    }
}
