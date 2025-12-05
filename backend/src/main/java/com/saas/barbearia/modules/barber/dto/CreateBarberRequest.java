package com.saas.barbearia.modules.barber.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateBarberRequest(
        @NotBlank String name,
        String document,
        Double commissionRate,
        String skills,
        UUID branchId
) {
}
