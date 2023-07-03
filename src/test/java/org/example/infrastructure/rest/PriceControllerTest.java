package org.example.infrastructure.rest;

import org.example.application.usecase.PriceUseCase;
import org.example.domain.model.Price;
import org.example.infrastructure.rest.mapper.PriceDtoMapper;
import org.example.rest.model.PriceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PriceControllerTest {

    private final PriceUseCase priceUseCase;

    private final PriceDtoMapper priceDtoMapper;

    private final PriceController priceController;

    public PriceControllerTest() {
        this.priceUseCase = mock(PriceUseCase.class);
        this.priceDtoMapper = mock(PriceDtoMapper.class);
        this.priceController = new PriceController(priceUseCase, priceDtoMapper);
    }

    @Test
    public void testGetPrices() throws Exception {
        LocalDateTime requestLocalDateTime = LocalDateTime.now();
        int brandIdRequest = 1;
        int productIdRequest = 12345;

        Price mockedDomain = new Price();
        mockedDomain.setProductId(12345);
        mockedDomain.setBrandId(1);
        mockedDomain.setPrice(35.50);
        mockedDomain.setCurrency("EUR");

        PriceDto mockedResponse = new PriceDto();
        mockedResponse.setProductId(12345);
        mockedResponse.setBrandId(1);
        mockedResponse.setPrice(35.50);
        mockedResponse.setCurrency("EUR");

        when(priceUseCase.getActivePrice(productIdRequest, brandIdRequest, requestLocalDateTime))
                .thenReturn(mockedDomain);
        when(priceDtoMapper.map(mockedDomain))
                .thenReturn(mockedResponse);

        ResponseEntity<PriceDto> response = priceController.pricesGet(requestLocalDateTime,productIdRequest, brandIdRequest);

        Assertions.assertNotNull(response);
        verify(priceUseCase, times(1))
                .getActivePrice(productIdRequest, brandIdRequest, requestLocalDateTime);
        verify(priceDtoMapper, times(1))
                .map(mockedDomain);
    }
}
