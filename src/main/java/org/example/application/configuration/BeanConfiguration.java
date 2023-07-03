package org.example.application.configuration;

import org.example.domain.service.PriceFilterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PriceFilterService priceFilterService() {
        return new PriceFilterService();
    }
}
