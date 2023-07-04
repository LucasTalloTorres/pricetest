package org.example.application.configuration;

import org.example.application.port.PriceRepositoryPort;
import org.example.application.usecase.PriceUseCase;
import org.example.domain.service.PriceFilterService;
import org.example.infrastructure.repository.PriceJpaRepository;
import org.example.infrastructure.repository.PriceRepository;
import org.example.infrastructure.repository.mapper.PriceDboMapper;
import org.example.infrastructure.repository.mapper.PriceDboMapperImpl;
import org.example.infrastructure.rest.PriceController;
import org.example.infrastructure.rest.mapper.PriceDtoMapper;
import org.example.infrastructure.rest.mapper.PriceDtoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PriceFilterService priceFilterService() {
        return new PriceFilterService();
    }

    @Bean
    public PriceRepositoryPort priceRepository(PriceJpaRepository priceJpaRepository, PriceDboMapper priceDboMapper) {
        return new PriceRepository(priceJpaRepository, priceDboMapper);
    }

    @Bean
    public PriceUseCase priceUseCase(PriceFilterService priceFilterService, PriceRepositoryPort priceRepository) {
        return new PriceUseCase(priceRepository, priceFilterService);
    }

    @Bean
    public PriceDtoMapper priceDtoMapper() {
        return new PriceDtoMapperImpl();
    }

    @Bean
    public PriceDboMapper priceDboMapper() {
        return new PriceDboMapperImpl();
    }

    @Bean
    public PriceController priceController(PriceUseCase priceUseCase, PriceDtoMapper priceDtoMapper) {
        return new PriceController(priceUseCase, priceDtoMapper);
    }

}
