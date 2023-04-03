import org.apache.camel.builder.RouteBuilder;

public class HelloCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        String winIn = "file:C:\\Users\\Desktop\\Documents\\MavenTest\\Hello_Maven\\in?noop=true";
        String linIn = "file:/home/udfa8@developer.atodnet.gov.au/Documents/HelloMavenCamel/in";
        String winOut = "file:C:\\Users\\Desktop\\Documents\\MavenTest\\Hello_Maven\\out";
        String linOut = "file:/home/udfa8@developer.atodnet.gov.au/Documents/HelloMavenCamel/out";
        from (linIn).to(linOut);
    }
}
