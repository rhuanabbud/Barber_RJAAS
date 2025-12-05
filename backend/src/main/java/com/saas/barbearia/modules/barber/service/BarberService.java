package com.saas.barbearia.modules.barber.service;

import com.saas.barbearia.modules.barber.BarberRepository;
import com.saas.barbearia.modules.barber.dto.BarberResponse;
import com.saas.barbearia.modules.barber.dto.CreateBarberRequest;
import com.saas.barbearia.modules.barber.model.Barber;
import com.saas.barbearia.modules.barbershop.BranchRepository;
import com.saas.barbearia.modules.barbershop.model.Branch;
import com.saas.barbearia.modules.common.service.TenantAwareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BarberService extends TenantAwareService {

    private final BarberRepository repository;
    private final BranchRepository branchRepository;

    public BarberService(BarberRepository repository, BranchRepository branchRepository) {
        this.repository = repository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public BarberResponse create(CreateBarberRequest request) {
        Barber barber = new Barber();
        barber.setName(request.name());
        barber.setDocument(request.document());
        barber.setCommissionRate(request.commissionRate());
        barber.setSkills(request.skills());
        barber.setTenantId(currentTenant());

        if (request.branchId() != null) {
            Branch branch = branchRepository.findByIdAndTenantId(request.branchId(), currentTenant())
                    .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada"));
            barber.setBranch(branch);
        }

        return toResponse(repository.save(barber));
    }

    @Transactional(readOnly = true)
    public List<BarberResponse> list(UUID branchId) {
        List<Barber> barbers = branchId == null
                ? repository.findByTenantId(currentTenant())
                : repository.findByBranchIdAndTenantId(branchId, currentTenant());
        return barbers.stream().map(this::toResponse).toList();
    }

    @Transactional
    public BarberResponse activate(UUID id, boolean active) {
        Barber barber = repository.findByIdAndTenantId(id, currentTenant())
                .orElseThrow(() -> new IllegalArgumentException("Barbeiro não encontrado"));
        barber.setActive(active);
        return toResponse(repository.save(barber));
    }

    private BarberResponse toResponse(Barber barber) {
        UUID branchId = barber.getBranch() != null ? barber.getBranch().getId() : null;
        return new BarberResponse(
                barber.getId(),
                barber.getName(),
                barber.getDocument(),
                barber.getCommissionRate(),
                barber.getSkills(),
                barber.isActive(),
                branchId
        );
    }
}
