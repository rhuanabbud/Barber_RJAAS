package com.saas.barbearia.modules.client.service;

import com.saas.barbearia.modules.client.ClientRepository;
import com.saas.barbearia.modules.client.dto.ClientResponse;
import com.saas.barbearia.modules.client.dto.CreateClientRequest;
import com.saas.barbearia.modules.client.model.Client;
import com.saas.barbearia.modules.common.service.TenantAwareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService extends TenantAwareService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClientResponse create(CreateClientRequest request) {
        Client client = new Client();
        client.setName(request.name());
        client.setEmail(request.email());
        client.setPhone(request.phone());
        client.setTenantId(currentTenant());
        return toResponse(repository.save(client));
    }

    @Transactional(readOnly = true)
    public List<ClientResponse> list() {
        return repository.findByTenantId(currentTenant()).stream()
                .map(this::toResponse)
                .toList();
    }

    private ClientResponse toResponse(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.getLoyaltyPoints());
    }
}
