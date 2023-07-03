package org.example.infraastructure.repository.mapper;

import org.example.domain.model.Price;
import org.example.infraastructure.repository.entity.PriceDbo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PriceDboMapper {

    Price map(PriceDbo price);

    List<Price> map(List<PriceDbo> prices);
}
