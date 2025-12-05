package com.saas.barbearia.modules.appointment.service;

import com.saas.barbearia.modules.appointment.AppointmentRepository;
import com.saas.barbearia.modules.appointment.dto.AppointmentResponse;
import com.saas.barbearia.modules.appointment.dto.CreateAppointmentRequest;
import com.saas.barbearia.modules.appointment.dto.UpdateStatusRequest;
import com.saas.barbearia.modules.appointment.model.Appointment;
import com.saas.barbearia.modules.appointment.model.AppointmentStatus;
import com.saas.barbearia.modules.barber.BarberRepository;
import com.saas.barbearia.modules.barber.model.Barber;
import com.saas.barbearia.modules.barbershop.BranchRepository;
import com.saas.barbearia.modules.barbershop.model.Branch;
import com.saas.barbearia.modules.client.ClientRepository;
import com.saas.barbearia.modules.client.model.Client;
import com.saas.barbearia.modules.common.service.TenantAwareService;
import com.saas.barbearia.modules.service.ServiceRepository;
import com.saas.barbearia.modules.service.model.ServiceItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService extends TenantAwareService {

    private final AppointmentRepository repository;
    private final BranchRepository branchRepository;
    private final BarberRepository barberRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;

    public AppointmentService(AppointmentRepository repository,
                              BranchRepository branchRepository,
                              BarberRepository barberRepository,
                              ClientRepository clientRepository,
                              ServiceRepository serviceRepository) {
        this.repository = repository;
        this.branchRepository = branchRepository;
        this.barberRepository = barberRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public AppointmentResponse create(CreateAppointmentRequest request) {
        Branch branch = branchRepository.findByIdAndTenantId(request.branchId(), currentTenant())
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada"));
        Barber barber = barberRepository.findByIdAndTenantId(request.barberId(), currentTenant())
                .orElseThrow(() -> new IllegalArgumentException("Barbeiro não encontrado"));
        Client client = clientRepository.findByIdAndTenantId(request.clientId(), currentTenant())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        ServiceItem service = serviceRepository.findById(request.serviceId())
                .filter(item -> currentTenant().equals(item.getTenantId()))
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        Appointment appointment = new Appointment();
        appointment.setBranch(branch);
        appointment.setBarber(barber);
        appointment.setClient(client);
        appointment.setService(service);
        appointment.setStart(request.start());
        appointment.setDurationMinutes(request.durationMinutes() > 0 ? request.durationMinutes() : service.getDurationMinutes());
        appointment.setPrice(service.getPrice());
        appointment.setNotes(request.notes());
        appointment.setTenantId(currentTenant());
        return toResponse(repository.save(appointment));
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponse> listForDay(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return repository.findByTenantIdAndStartBetween(currentTenant(), start, end)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public AppointmentResponse updateStatus(UUID id, UpdateStatusRequest request) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        ensureOwnership(appointment);
        appointment.setStatus(request.status());
        return toResponse(repository.save(appointment));
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponse> byStatus(AppointmentStatus status) {
        return repository.findByTenantIdAndStatus(currentTenant(), status).stream()
                .map(this::toResponse)
                .toList();
    }

    private AppointmentResponse toResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getBranch() != null ? appointment.getBranch().getId() : null,
                appointment.getBarber() != null ? appointment.getBarber().getId() : null,
                appointment.getClient() != null ? appointment.getClient().getId() : null,
                appointment.getService() != null ? appointment.getService().getId() : null,
                appointment.getStart(),
                appointment.getDurationMinutes(),
                appointment.getStatus(),
                appointment.getPrice(),
                appointment.getNotes()
        );
    }
}
