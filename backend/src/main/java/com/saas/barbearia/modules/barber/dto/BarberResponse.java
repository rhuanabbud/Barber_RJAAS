package com.saas.barbearia.modules.barber.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BarberResponse(
        UUID id,
        String name,
        String document,
        BigDecimal commissionRate,
        String skills,
        boolean active,
        UUID branchId
) {
}
