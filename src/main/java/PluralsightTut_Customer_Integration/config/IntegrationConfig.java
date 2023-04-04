package PluralsightTut_Customer_Integration.config;

import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(basePackages = "PluralsightTut_Customer_Integration.addressUpdateRoute")
public class IntegrationConfig {

    @Bean(name = "csvDataFormatAddressUpdate")
    public CsvDataFormat csvDataFormatAddressUpdate() {
        CsvDataFormat csvDataFormatAddressUpdate = new CsvDataFormat();
        csvDataFormatAddressUpdate.setDelimiter(",");
        csvDataFormatAddressUpdate.setSkipHeaderRecord("true");
        return csvDataFormatAddressUpdate;
    }

}