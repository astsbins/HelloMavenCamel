package addressupdateroute;

import PluralsightTut_Customer_Integration.addressUpdateRoute.AddressUpdatesToCustomerServiceRoute;
import PluralsightTut_Customer_Integration.config.IntegrationConfig;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ToDynamicDefinition;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpointsAndSkip;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@CamelSpringBootTest
@SpringBootApplication
@ContextConfiguration(classes = {AddressUpdatesToCustomerServiceRoute.class, IntegrationConfig.class})
@TestPropertySource(locations = "file:///home/udfa8@developer.atodnet.gov.au/Documents/HelloMavenCamel/src/test/resources/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration(classes = {AddressUpdatesToCustomerServiceRoute.class})
@MockEndpointsAndSkip("file:.*|rest:.*")
@UseAdviceWith
public class AddressUpdatesToCustomerServiceRouteTest {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject("mock://rest:patch:customer")
    private MockEndpoint restEndpoint;



    @Value("classpath:data/customer-address-update-valid.csv")
    private Resource customerAddressUpdateFileValidResource;

    @Test
    public void route_testValid() throws Exception {
        GenericFile file = new GenericFile();
        file.setFile(customerAddressUpdateFileValidResource.getFile());

        MockEndpoint restEndpoint = camelContext.getEndpoint(
                "mock://rest:patch:customer", MockEndpoint.class);

        // Replace rest endpoint with dynamic
        AdviceWith.adviceWith(camelContext,
                "address-updates-to-customer-service-route",
                rb -> rb.weaveByType(ToDynamicDefinition.class).replace()
                        .toD("mock://rest:patch:customer"));

        // Mock out starter file
        AdviceWith.adviceWith(camelContext,
                "address-updates-to-customer-service-route",
                rb -> rb.replaceFromWith("direct:file:start"));
        camelContext.start();
        restEndpoint.expectedMessageCount(1);
        producerTemplate.sendBody("direct:file:start", file);
        restEndpoint.assertIsSatisfied();



    }
}
