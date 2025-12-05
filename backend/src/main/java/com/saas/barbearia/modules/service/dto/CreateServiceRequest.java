package com.saas.barbearia.modules.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateServiceRequest(
        @NotBlank String name,
        @Min(0) int durationMinutes,
        @NotNull BigDecimal price,
        Integer discountPercent
) {
}
