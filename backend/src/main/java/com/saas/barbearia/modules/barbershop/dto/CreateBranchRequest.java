package com.saas.barbearia.modules.barbershop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateBranchRequest(
        @NotBlank String name,
        String address,
        String phone,
        @NotNull UUID barbershopId
) {
}
