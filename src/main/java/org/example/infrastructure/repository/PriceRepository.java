package org.example.infrastructure.repository;

import org.example.domain.model.Price;
import org.example.infrastructure.repository.entity.PriceDbo;
import org.example.infrastructure.repository.mapper.PriceDboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceRepository {

    private final PriceJpaRepository repository;
    private final PriceDboMapper mapper;

    @Autowired
    public PriceRepository(PriceJpaRepository repository, PriceDboMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Price> getActivePrices(int productId, int brandId, LocalDateTime dateTime) {
        List<PriceDbo> priceDbo = repository.findActivePrices(productId, brandId, dateTime);
        return mapper.map(priceDbo);
    }
}
