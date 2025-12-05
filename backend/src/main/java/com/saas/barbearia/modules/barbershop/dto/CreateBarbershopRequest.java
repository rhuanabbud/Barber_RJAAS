package com.saas.barbearia.modules.barbershop.dto;

import com.saas.barbearia.modules.barbershop.model.PlanType;
import jakarta.validation.constraints.NotBlank;

public record CreateBarbershopRequest(
        @NotBlank String name,
        @NotBlank String document,
        String phone,
        String email,
        PlanType plan
) {
}
