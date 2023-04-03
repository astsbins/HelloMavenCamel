package PluralsightTut_CustomerIntegration.config;

import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationConfig {

    @Bean(name = "csvDataFormatAddressUpdate")
    public CsvDataFormat csvDataFormatAddressUpdate() {
        CsvDataFormat csvDataFormatAddressUpdate = new CsvDataFormat();
        csvDataFormatAddressUpdate.setDelimiter(",");
        csvDataFormatAddressUpdate.setSkipHeaderRecord(Boolean.valueOf("true"));
        return csvDataFormatAddressUpdate;
    }

}