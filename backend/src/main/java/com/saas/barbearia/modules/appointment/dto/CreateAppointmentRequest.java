package com.saas.barbearia.modules.appointment.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentRequest(
        @NotNull UUID branchId,
        @NotNull UUID barberId,
        @NotNull UUID clientId,
        @NotNull UUID serviceId,
        @NotNull @FutureOrPresent LocalDateTime start,
        int durationMinutes,
        String notes
) {
}
