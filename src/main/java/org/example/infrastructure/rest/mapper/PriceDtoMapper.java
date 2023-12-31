package org.example.infrastructure.rest.mapper;

import org.example.domain.model.Price;
import org.example.rest.model.PriceDto;
import org.mapstruct.Mapper;


@Mapper
public interface PriceDtoMapper {

    PriceDto map(Price price);

}
