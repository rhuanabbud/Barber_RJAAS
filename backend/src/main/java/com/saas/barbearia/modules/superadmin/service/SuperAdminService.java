package com.saas.barbearia.modules.superadmin.service;

import com.saas.barbearia.modules.appointment.AppointmentRepository;
import com.saas.barbearia.modules.barber.BarberRepository;
import com.saas.barbearia.modules.barbershop.BarbershopRepository;
import com.saas.barbearia.modules.client.ClientRepository;
import com.saas.barbearia.modules.service.ServiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class SuperAdminService {

    private final BarbershopRepository barbershopRepository;
    private final BarberRepository barberRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final AppointmentRepository appointmentRepository;

    public SuperAdminService(BarbershopRepository barbershopRepository,
                             BarberRepository barberRepository,
                             ClientRepository clientRepository,
                             ServiceRepository serviceRepository,
                             AppointmentRepository appointmentRepository) {
        this.barbershopRepository = barbershopRepository;
        this.barberRepository = barberRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public SuperAdminSummary buildSummary() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);
        return new SuperAdminSummary(
                barbershopRepository.count(),
                barberRepository.count(),
                clientRepository.count(),
                serviceRepository.count(),
                appointmentRepository.countByStartBetween(start, end)
        );
    }
}
