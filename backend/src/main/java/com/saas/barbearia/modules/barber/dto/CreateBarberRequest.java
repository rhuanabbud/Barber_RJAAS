package com.saas.barbearia.modules.barber.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateBarberRequest(
        @NotBlank String name,
        String document,
        BigDecimal commissionRate,
        String skills,
        UUID branchId
) {
}
