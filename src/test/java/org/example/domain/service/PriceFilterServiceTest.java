package org.example.domain.service;

import org.example.domain.model.Price;
import org.example.domain.model.PriceTest;
import org.example.domain.service.exception.PriceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PriceFilterServiceTest {

    private PriceFilterService priceFilterService;

    @BeforeEach
    public void setup() {
        priceFilterService = new PriceFilterService();
    }

    @Test
    public void testPriceNotFoundException_WhenNoPricesFound() {
        int productId = 12345;
        int brandId = 1;
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        List<Price> prices = new ArrayList<>();

        Assertions.assertThrows(PriceNotFoundException.class, () -> priceFilterService.getActivePrice(prices, productId, brandId, dateTime));
    }

    @Test
    public void testReturnActivePrice_WhenPriceIsFound() {
        int productId = 12345;
        int brandId = 1;
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 12, 0);

        List<Price> prices = new ArrayList<>();

        prices.add(Price.builder()
                .startDate(LocalDateTime.of(2021, 12, 31, 0, 0))
                .endDate(LocalDateTime.of(2022, 1, 31, 23, 59))
                .brandId(1).priceList(1).productId(12345).priority(1).price(19.99).currency("EUR").build());
        prices.add(Price.builder()
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0))
                .endDate(LocalDateTime.of(2022, 1, 31, 23, 59))
                .brandId(1).priceList(2).productId(12345).priority(1).price(25.99).currency("EUR").build());
        prices.add(Price.builder()
                .startDate(LocalDateTime.of(2022, 1, 1, 0, 0))
                .endDate(LocalDateTime.of(2022, 1, 31, 23, 59))
                .brandId(2).priceList(1).productId(12345).priority(1).price(22.99).currency("EUR").build());

        Price result = priceFilterService.getActivePrice(prices, productId, brandId, dateTime);
        Assertions.assertEquals(19.99, result.getPrice());
    }
}