package com.saas.barbearia.modules.barbershop.api;

import com.saas.barbearia.modules.barbershop.dto.BarbershopResponse;
import com.saas.barbearia.modules.barbershop.dto.BranchResponse;
import com.saas.barbearia.modules.barbershop.dto.CreateBarbershopRequest;
import com.saas.barbearia.modules.barbershop.dto.CreateBranchRequest;
import com.saas.barbearia.modules.barbershop.service.BarbershopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/barbershops")
public class BarbershopController {

    private final BarbershopService service;

    public BarbershopController(BarbershopService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarbershopResponse create(@Valid @RequestBody CreateBarbershopRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<BarbershopResponse> list() {
        return service.list();
    }

    @PostMapping("/{barbershopId}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    public BranchResponse addBranch(@PathVariable UUID barbershopId, @Valid @RequestBody CreateBranchRequest request) {
        CreateBranchRequest payload = new CreateBranchRequest(
                request.name(),
                request.address(),
                request.phone(),
                barbershopId
        );
        return service.addBranch(payload);
    }

    @GetMapping("/{barbershopId}/branches")
    public List<BranchResponse> listBranches(@PathVariable UUID barbershopId) {
        return service.listBranches(barbershopId);
    }
}
