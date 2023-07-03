package org.example.application.usecase;

import org.example.domain.model.Price;
import org.example.domain.service.PriceFilterService;
import org.example.infraastructure.repository.PriceJpaRepository;
import org.example.infraastructure.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceUseCase {
    private final PriceRepository priceRepository;
    private final PriceFilterService priceFilterService;

    @Autowired
    public PriceUseCase(PriceRepository priceRepository, PriceFilterService priceFilterService) {
        this.priceRepository = priceRepository;
        this.priceFilterService = priceFilterService;
    }

    public Price getActivePrice(int productId, int brandId, LocalDateTime dateTime) {
        List<Price> prices = priceRepository.getActivePrices(productId, brandId, dateTime);
        return priceFilterService.getActivePrice(prices, productId, brandId, dateTime);
    }
}
