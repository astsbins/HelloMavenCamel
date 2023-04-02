import org.apache.camel.builder.RouteBuilder;

public class HelloCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from ("file:C:\\Users\\Desktop\\Documents\\MavenTest\\Hello_Maven\\in?noop=true").to("file:C:\\Users\\Desktop\\Documents\\MavenTest\\Hello_Maven\\out");
    }
}
