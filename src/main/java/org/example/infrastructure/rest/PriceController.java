package org.example.infrastructure.rest;

import jakarta.validation.constraints.NotNull;
import org.example.application.usecase.PriceUseCase;
import org.example.domain.service.exception.PriceNotFoundException;
import org.example.infrastructure.rest.exception.ApiError;
import org.example.infrastructure.rest.mapper.PriceDtoMapper;
import org.example.rest.PricesApi;
import org.example.rest.model.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PriceController implements PricesApi {

    private final PriceUseCase priceUseCase;

    private final PriceDtoMapper priceDtoMapper;

    @Autowired
    public PriceController(PriceUseCase priceUseCase, PriceDtoMapper priceDtoMapper) {
        this.priceUseCase = priceUseCase;
        this.priceDtoMapper = priceDtoMapper;
    }

    @Override
    public ResponseEntity<PriceDto> pricesGet(@NotNull LocalDateTime applicationDate,
                                              @NotNull Integer productId, @NotNull Integer brandId) {
        return ResponseEntity.ok(priceDtoMapper.map(priceUseCase.getActivePrice(productId, brandId, applicationDate)));
    }

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleHttpRequestMethodNotSupported(PriceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }
}
