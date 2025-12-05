package com.saas.barbearia.modules.barbershop.dto;

import java.util.UUID;

public record BranchResponse(
        UUID id,
        String name,
        String address,
        String phone,
        UUID barbershopId
) {
}
