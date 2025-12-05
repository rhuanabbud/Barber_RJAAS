package com.saas.barbearia.modules.barber.model;

import com.saas.barbearia.modules.barbershop.model.Branch;
import com.saas.barbearia.modules.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "barbers")
@Getter
@Setter
public class Barber extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String document;

    @Column(precision = 8, scale = 2)
    private BigDecimal commissionRate;

    private String skills;

    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
