package com.saas.barbearia.modules.service;

import com.saas.barbearia.modules.service.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceItem, UUID> {
    List<ServiceItem> findByTenantId(UUID tenantId);
}
