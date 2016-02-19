package server;
//
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


/**
 * Created by chenjinlong on 16/2/4.
 */
public class JettyServer {
    public static void main(String[] args) {
        Server server = new Server(8095);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("./mango-admin/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("./mango-admin/src/main/webapp");
        //解决静态资源缓存后再ide里面不能修改问题
        context.setDefaultsDescriptor("./mango-admin/src/test/resources/webdefault.xml");

        context.setParentLoaderPriority(true);

        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
