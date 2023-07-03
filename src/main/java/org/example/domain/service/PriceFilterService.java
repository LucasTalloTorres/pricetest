package org.example.domain.service;

import org.example.domain.model.Price;
import org.example.domain.service.exception.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class PriceFilterService {

    public Price getActivePrice(List<Price> prices, int productId, int brandId, LocalDateTime dateTime) {
        return prices.stream()
                .filter(price -> price.getProductId() == productId)
                .filter(price -> price.getBrandId() == brandId)
                .filter(price -> price.getStartDate().isBefore(dateTime) && price.getEndDate().isAfter(dateTime))
                .sorted(Comparator.comparingInt(Price::getPriority).reversed())
                .findFirst()
                .orElseThrow(() -> new PriceNotFoundException("Not found any price"));
    }
}
