package com.cos.common.config.dubbo;

import org.apache.dubbo.config.MetadataReportConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig {

    @Value("${dubbo.metadata.address}")
    private String metadataAddress;

    @Bean
    public MetadataReportConfig metadataReportConfig() {
        MetadataReportConfig metadataReportConfig = new MetadataReportConfig();
//        metadataReportConfig.setAddress("zookeeper://118.25.83.43:2181");
        metadataReportConfig.setAddress(metadataAddress);
        return metadataReportConfig;
    }

}
