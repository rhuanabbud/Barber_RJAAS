package com.saas.barbearia.modules.barbershop;

import com.saas.barbearia.modules.barbershop.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    List<Branch> findByTenantId(UUID tenantId);
    Optional<Branch> findByIdAndTenantId(UUID id, UUID tenantId);
    List<Branch> findByBarbershopIdAndTenantId(UUID barbershopId, UUID tenantId);
}
