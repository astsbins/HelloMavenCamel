package PluralsightTut_Customer_Integration.addressUpdateRoute;
import PluralsightTut_Customer_Integration.addressUpdateRoute.processor.AddressUpdateLineToCustomerMapper;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import PluralsightTut_Customer_Integration.config.IntegrationConfig;

@Component //spring component
public class AddressUpdatesToCustomerServiceRoute extends RouteBuilder {

    private org.apache.camel.model.dataformat.CsvDataFormat csvDataFormatAddressUpdate;

    public AddressUpdatesToCustomerServiceRoute(
            @Qualifier("csvDataFormatAddressUpdate")
                    CsvDataFormat csvDataFormatAddressUpdate) {
        this.csvDataFormatAddressUpdate =
                csvDataFormatAddressUpdate;
    }

    @Override
    public void configure() throws Exception {
        // From definition - immediate polling of the shared directory for any
        // files that match the include pattern. Once a file is processed, its
        // archived to the directory specified by the move option.
        from("file:{{app.addressToCustomerRoute.directory}}" +
                "?include={{app.addressToCustomerRoute.includeFile}}" +
                "&move={{app.addressToCustomerRoute.moveDirectory}}")
                // ID of the route
                .routeId("address-updates-to-customer-service-route")
                // Unmarshals from the GenericFile type input by the from definition
                // into a list of rows
                .unmarshal(csvDataFormatAddressUpdate)
                // Splitter will split the list of rows from the file into individual
                // messages for execution. The body() is a way to reference the exchange
                // body's input.
                .split(body())
                // Bean definition to execute the mapper's process method, passing the body
                // as a list of strings representing the row that was previously split
                .bean(AddressUpdateLineToCustomerMapper.class, "process")
                // Exchange property sourced by using Simple Expression to get the customer
                // ID off the exchange input message's body
                .setProperty("customerId", simple("${body.id}"))
                // Marshal to JSON using the Jackson library
                .marshal().json()
                // Route to a dynamic to definition with the path including the
                // exchange property previously set.
                .toD("rest:patch:customer/${exchangeProperty.customerId}" +
                                "?host={{app.customer-service.host}}");
    }
}


