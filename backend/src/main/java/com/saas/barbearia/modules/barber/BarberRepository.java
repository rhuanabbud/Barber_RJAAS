package com.saas.barbearia.modules.barber;

import com.saas.barbearia.modules.barber.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BarberRepository extends JpaRepository<Barber, UUID> {
    List<Barber> findByTenantId(UUID tenantId);
    List<Barber> findByBranchIdAndTenantId(UUID branchId, UUID tenantId);
    Optional<Barber> findByIdAndTenantId(UUID id, UUID tenantId);
}
