package com.saas.barbearia.modules.barber.api;

import com.saas.barbearia.modules.barber.dto.BarberResponse;
import com.saas.barbearia.modules.barber.dto.CreateBarberRequest;
import com.saas.barbearia.modules.barber.service.BarberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    private final BarberService service;

    public BarberController(BarberService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarberResponse create(@Valid @RequestBody CreateBarberRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<BarberResponse> list(@RequestParam(required = false) UUID branchId) {
        return service.list(branchId);
    }

    @PatchMapping("/{id}/status")
    public BarberResponse toggle(@PathVariable UUID id, @RequestParam(defaultValue = "true") boolean active) {
        return service.activate(id, active);
    }
}
