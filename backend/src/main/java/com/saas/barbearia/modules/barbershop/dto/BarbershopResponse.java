package com.saas.barbearia.modules.barbershop.dto;

import com.saas.barbearia.modules.barbershop.model.PlanType;

import java.util.UUID;

public record BarbershopResponse(
        UUID id,
        String name,
        String document,
        String phone,
        String email,
        PlanType plan
) {
}
