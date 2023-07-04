package org.example.application.usecase;

import lombok.RequiredArgsConstructor;
import org.example.application.port.PriceRepositoryPort;
import org.example.domain.model.Price;
import org.example.domain.service.PriceFilterService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PriceUseCase {

    private final PriceRepositoryPort priceRepository;
    private final PriceFilterService priceFilterService;


    public Price getActivePrice(int productId, int brandId, LocalDateTime dateTime) {
        List<Price> prices = priceRepository.getActivePrices(productId, brandId, dateTime);
        return priceFilterService.getActivePrice(prices, productId, brandId, dateTime);
    }
}
