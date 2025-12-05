package com.saas.barbearia.modules.service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ServiceResponse(
        UUID id,
        String name,
        int durationMinutes,
        BigDecimal price,
        Integer discountPercent
) {
}
