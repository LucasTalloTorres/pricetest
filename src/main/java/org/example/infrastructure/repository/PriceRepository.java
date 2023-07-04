package org.example.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.example.application.port.PriceRepositoryPort;
import org.example.domain.model.Price;
import org.example.infrastructure.repository.entity.PriceDbo;
import org.example.infrastructure.repository.mapper.PriceDboMapper;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PriceRepository implements PriceRepositoryPort {

    private final PriceJpaRepository repository;
    private final PriceDboMapper mapper;

    public List<Price> getActivePrices(int productId, int brandId, LocalDateTime dateTime) {
        List<PriceDbo> priceDbo = repository.findActivePrices(productId, brandId, dateTime);
        return mapper.map(priceDbo);
    }
}
