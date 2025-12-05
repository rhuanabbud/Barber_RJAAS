package com.saas.barbearia.modules.appointment.api;

import com.saas.barbearia.modules.appointment.dto.AppointmentResponse;
import com.saas.barbearia.modules.appointment.dto.CreateAppointmentRequest;
import com.saas.barbearia.modules.appointment.dto.UpdateStatusRequest;
import com.saas.barbearia.modules.appointment.model.AppointmentStatus;
import com.saas.barbearia.modules.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse create(@Valid @RequestBody CreateAppointmentRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<AppointmentResponse> listForDay(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate target = date != null ? date : LocalDate.now();
        return service.listForDay(target);
    }

    @PatchMapping("/{id}/status")
    public AppointmentResponse updateStatus(@PathVariable UUID id, @Valid @RequestBody UpdateStatusRequest request) {
        return service.updateStatus(id, request);
    }

    @GetMapping("/status/{status}")
    public List<AppointmentResponse> byStatus(@PathVariable AppointmentStatus status) {
        return service.byStatus(status);
    }
}
