package org.example.infrastructure.repository;

import org.example.domain.model.Price;
import org.example.infrastructure.repository.entity.PriceDbo;
import org.example.infrastructure.repository.mapper.PriceDboMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class PriceRepositoryTest {

    @Mock
    private PriceJpaRepository repository;

    @Mock
    private PriceDboMapper mapper;

    private PriceRepository priceRepository;

    @BeforeEach
    public void setup() {
        repository = mock(PriceJpaRepository.class);
        mapper = mock(PriceDboMapper.class);
        priceRepository = new PriceRepository(repository, mapper);
    }

    @Test
    public void testGetActivePrice() {
        int productId = 35455;
        int brandId = 1;
        LocalDateTime dateTime = LocalDateTime.of(2022, 6, 1, 10, 0);

        List<PriceDbo> mockedPriceDboList = List.of(mock(PriceDbo.class));
        List<Price> mockedPriceList = List.of(mock(Price.class));

        when(repository.findActivePrices(productId, brandId, dateTime)).thenReturn(mockedPriceDboList);
        when(mapper.map(mockedPriceDboList)).thenReturn(mockedPriceList);

        List<Price> result = priceRepository.getActivePrices(productId, brandId, dateTime);

        verify(repository).findActivePrices(productId, brandId, dateTime);
        verify(mapper).map(mockedPriceDboList);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockedPriceList, result);
    }
}