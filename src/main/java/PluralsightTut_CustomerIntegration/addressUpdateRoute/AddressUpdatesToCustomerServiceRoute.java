package PluralsightTut_CustomerIntegration.addressUpdateRoute;
import PluralsightTut_CustomerIntegration.addressUpdateRoute.processor.AddressUpdateLineToCustomerMapper;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddressUpdatesToCustomerServiceRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    }
}