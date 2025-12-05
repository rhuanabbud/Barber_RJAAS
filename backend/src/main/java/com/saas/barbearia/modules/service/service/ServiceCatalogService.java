package com.saas.barbearia.modules.service.service;

import com.saas.barbearia.modules.common.service.TenantAwareService;
import com.saas.barbearia.modules.service.ServiceRepository;
import com.saas.barbearia.modules.service.dto.CreateServiceRequest;
import com.saas.barbearia.modules.service.dto.ServiceResponse;
import com.saas.barbearia.modules.service.model.ServiceItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceCatalogService extends TenantAwareService {

    private final ServiceRepository repository;

    public ServiceCatalogService(ServiceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ServiceResponse create(CreateServiceRequest request) {
        ServiceItem item = new ServiceItem();
        item.setName(request.name());
        item.setDurationMinutes(request.durationMinutes());
        item.setPrice(request.price());
        item.setDiscountPercent(request.discountPercent());
        item.setTenantId(currentTenant());
        return toResponse(repository.save(item));
    }

    @Transactional(readOnly = true)
    public List<ServiceResponse> list() {
        return repository.findByTenantId(currentTenant()).stream()
                .map(this::toResponse)
                .toList();
    }

    private ServiceResponse toResponse(ServiceItem item) {
        return new ServiceResponse(item.getId(), item.getName(), item.getDurationMinutes(), item.getPrice(), item.getDiscountPercent());
    }
}
