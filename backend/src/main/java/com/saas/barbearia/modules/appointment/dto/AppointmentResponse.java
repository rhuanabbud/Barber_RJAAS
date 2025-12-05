package com.saas.barbearia.modules.appointment.dto;

import com.saas.barbearia.modules.appointment.model.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID branchId,
        UUID barberId,
        UUID clientId,
        UUID serviceId,
        LocalDateTime start,
        int durationMinutes,
        AppointmentStatus status,
        BigDecimal price,
        String notes
) {
}
