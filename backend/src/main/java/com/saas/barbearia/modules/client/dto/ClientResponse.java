package com.saas.barbearia.modules.client.dto;

import java.util.UUID;

public record ClientResponse(
        UUID id,
        String name,
        String email,
        String phone,
        int loyaltyPoints
) {
}
