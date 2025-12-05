package com.saas.barbearia.modules.appointment.model;

import com.saas.barbearia.modules.barber.model.Barber;
import com.saas.barbearia.modules.barbershop.model.Branch;
import com.saas.barbearia.modules.client.model.Client;
import com.saas.barbearia.modules.common.model.BaseEntity;
import com.saas.barbearia.modules.service.model.ServiceItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceItem service;

    private LocalDateTime start;
    private int durationMinutes;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    private BigDecimal price;

    private String notes;
}
