package com.saas.barbearia.modules.client.api;

import com.saas.barbearia.modules.client.dto.ClientResponse;
import com.saas.barbearia.modules.client.dto.CreateClientRequest;
import com.saas.barbearia.modules.client.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse create(@Valid @RequestBody CreateClientRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ClientResponse> list() {
        return service.list();
    }
}
