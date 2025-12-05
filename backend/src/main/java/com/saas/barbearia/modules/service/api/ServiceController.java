package com.saas.barbearia.modules.service.api;

import com.saas.barbearia.modules.service.dto.CreateServiceRequest;
import com.saas.barbearia.modules.service.dto.ServiceResponse;
import com.saas.barbearia.modules.service.service.ServiceCatalogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceCatalogService service;

    public ServiceController(ServiceCatalogService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse create(@Valid @RequestBody CreateServiceRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ServiceResponse> list() {
        return service.list();
    }
}
