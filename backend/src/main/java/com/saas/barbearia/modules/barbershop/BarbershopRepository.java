package com.saas.barbearia.modules.barbershop;

import com.saas.barbearia.modules.barbershop.model.Barbershop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BarbershopRepository extends JpaRepository<Barbershop, UUID> {
    List<Barbershop> findByTenantId(UUID tenantId);
    Optional<Barbershop> findByIdAndTenantId(UUID id, UUID tenantId);
    Optional<Barbershop> findByDocumentAndTenantId(String document, UUID tenantId);
}
