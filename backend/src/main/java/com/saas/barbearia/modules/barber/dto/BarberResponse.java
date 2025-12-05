package com.saas.barbearia.modules.barber.dto;

import java.util.UUID;

public record BarberResponse(
        UUID id,
        String name,
        String document,
        Double commissionRate,
        String skills,
        boolean active,
        UUID branchId
) {
}
