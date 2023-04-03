import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

    public static void main(String[] args) {
        //HelloCamelRoute routeBuilder = new HelloCamelRoute();
        HelloCamelConsole routeBuilder = new HelloCamelConsole();
        CamelContext ctx = new DefaultCamelContext();
        try {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(50 * 1000);
            ctx.stop();
            System.out.println("stopped");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}