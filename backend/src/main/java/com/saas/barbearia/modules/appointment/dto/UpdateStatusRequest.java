package com.saas.barbearia.modules.appointment.dto;

import com.saas.barbearia.modules.appointment.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(@NotNull AppointmentStatus status) {
}
