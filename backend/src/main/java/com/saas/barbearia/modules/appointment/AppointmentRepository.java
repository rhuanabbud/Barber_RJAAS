package com.saas.barbearia.modules.appointment;

import com.saas.barbearia.modules.appointment.model.Appointment;
import com.saas.barbearia.modules.appointment.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByTenantIdAndStartBetween(UUID tenantId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByBarberIdAndTenantId(UUID barberId, UUID tenantId);
    List<Appointment> findByClientIdAndTenantId(UUID clientId, UUID tenantId);
    List<Appointment> findByTenantIdAndStatus(UUID tenantId, AppointmentStatus status);
    long countByStartBetween(LocalDateTime start, LocalDateTime end);
}
