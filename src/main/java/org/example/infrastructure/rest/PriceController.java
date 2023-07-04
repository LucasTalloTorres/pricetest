package org.example.infrastructure.rest;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.application.usecase.PriceUseCase;
import org.example.infrastructure.rest.mapper.PriceDtoMapper;
import org.example.rest.PricesApi;
import org.example.rest.model.PriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final PriceUseCase priceUseCase;

    private final PriceDtoMapper priceDtoMapper;

    @Override
    public ResponseEntity<PriceDto> pricesGet(@NotNull LocalDateTime applicationDate,
                                              @NotNull Integer productId, @NotNull Integer brandId) {
        return ResponseEntity.ok(priceDtoMapper.map(priceUseCase.getActivePrice(productId, brandId, applicationDate)));
    }

}
