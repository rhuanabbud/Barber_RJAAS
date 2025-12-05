package com.saas.barbearia.modules.service.model;

import com.saas.barbearia.modules.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
@Getter
@Setter
public class ServiceItem extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private int durationMinutes;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer discountPercent;
}
