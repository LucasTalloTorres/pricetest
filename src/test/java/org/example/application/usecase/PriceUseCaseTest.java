package org.example.application.usecase;

import org.example.domain.model.Price;
import org.example.domain.service.PriceFilterService;
import org.example.infraastructure.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class PriceUseCaseTest {

    private PriceRepository priceRepository;
    private PriceFilterService priceFilterService;
    private PriceUseCase priceUseCase;

    @BeforeEach
    public void setup() {
        priceRepository = mock(PriceRepository.class);
        priceFilterService = mock(PriceFilterService.class);
        priceUseCase = new PriceUseCase(priceRepository, priceFilterService);
    }

    @Test
    public void testGetActivePrice_ReturnsPrice_WhenPriceExists() {
        int productId = 12345;
        int brandId = 1;
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 12, 0);

        Price expectedPrice = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2021, 12, 31, 0, 0))
                .endDate(LocalDateTime.of(2022, 1, 31, 23, 59))
                .priceList(1)
                .productId(12345)
                .priority(1)
                .price(19.99)
                .currency("EUR")
                .build();
        List<Price> listPrices = List.of(expectedPrice);

        when(priceRepository.getActivePrices(productId, brandId, dateTime))
                .thenReturn(listPrices);
        when(priceFilterService.getActivePrice(listPrices, productId, brandId, dateTime))
                .thenReturn(expectedPrice);

        Price result = priceUseCase.getActivePrice(productId, brandId, dateTime);

        Assertions.assertEquals(expectedPrice, result);
        verify(priceRepository, times(1)).getActivePrices(productId, brandId, dateTime);
        verify(priceFilterService, times(1)).getActivePrice(listPrices, productId, brandId, dateTime);
    }
}
