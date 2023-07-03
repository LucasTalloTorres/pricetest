package org.example.infrastructure.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
@NoArgsConstructor
public class PriceDbo {

    @Id
    private Integer priceList;
    private Integer brandId;
    private Integer productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double price;
    @Column(name = "CURR")
    private String currency;
}
