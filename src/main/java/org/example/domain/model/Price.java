package org.example.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Price {
    private Integer priceList;
    private Integer brandId;
    private Integer productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double price;
    private String currency;
}
