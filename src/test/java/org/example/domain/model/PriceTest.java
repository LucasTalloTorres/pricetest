package org.example.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PriceTest {

    @Test
    public void testPriceObjectCreation() {
        int brandId = 1;
        LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 1, 31, 23, 59);
        int priceList = 1;
        int productId = 12345;
        int priority = 1;
        double price = 19.99;
        String currency = "EUR";

        Price priceObject = Price.builder()
            .brandId(brandId)
            .startDate(startDate)
            .endDate(endDate)
            .priceList(priceList)
            .productId(productId)
            .priority(priority)
            .price(price)
            .currency(currency)
            .build();

        Assertions.assertEquals(brandId, priceObject.getBrandId());
        Assertions.assertEquals(startDate, priceObject.getStartDate());
        Assertions.assertEquals(endDate, priceObject.getEndDate());
        Assertions.assertEquals(priceList, priceObject.getPriceList());
        Assertions.assertEquals(productId, priceObject.getProductId());
        Assertions.assertEquals(priority, priceObject.getPriority());
        Assertions.assertEquals(price, priceObject.getPrice());
        Assertions.assertEquals(currency, priceObject.getCurrency());
    }
}
