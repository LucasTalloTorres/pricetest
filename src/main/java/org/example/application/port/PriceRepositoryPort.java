package org.example.application.port;

import org.example.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {

    List<Price> getActivePrices(int productId, int brandId, LocalDateTime dateTime);
}
