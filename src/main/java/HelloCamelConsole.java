import org.apache.camel.builder.RouteBuilder;

public class HelloCamelConsole extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from ("stream://in?promptMessage=What to repeat: ")
                .process(
                   exchange ->
                           exchange.getIn().setBody(
                                   "You said: " + exchange.getIn()
                                   .getBody(String.class)
                                   )
                           )
                .to("stream://out");
    }
}
