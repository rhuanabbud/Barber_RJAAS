package com.saas.barbearia.modules.barbershop.service;

import com.saas.barbearia.modules.barbershop.BarbershopRepository;
import com.saas.barbearia.modules.barbershop.BranchRepository;
import com.saas.barbearia.modules.barbershop.dto.BarbershopResponse;
import com.saas.barbearia.modules.barbershop.dto.BranchResponse;
import com.saas.barbearia.modules.barbershop.dto.CreateBarbershopRequest;
import com.saas.barbearia.modules.barbershop.dto.CreateBranchRequest;
import com.saas.barbearia.modules.barbershop.model.Barbershop;
import com.saas.barbearia.modules.barbershop.model.Branch;
import com.saas.barbearia.modules.common.service.TenantAwareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BarbershopService extends TenantAwareService {

    private final BarbershopRepository barbershopRepository;
    private final BranchRepository branchRepository;

    public BarbershopService(BarbershopRepository barbershopRepository, BranchRepository branchRepository) {
        this.barbershopRepository = barbershopRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public BarbershopResponse create(CreateBarbershopRequest request) {
        barbershopRepository.findByDocumentAndTenantId(request.document(), currentTenant())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Documento já cadastrado");
                });

        Barbershop barbershop = new Barbershop();
        barbershop.setName(request.name());
        barbershop.setDocument(request.document());
        barbershop.setPhone(request.phone());
        barbershop.setEmail(request.email());
        if (request.plan() != null) {
            barbershop.setPlan(request.plan());
        }
        return toResponse(barbershopRepository.save(barbershop));
    }

    @Transactional(readOnly = true)
    public List<BarbershopResponse> list() {
        return barbershopRepository.findByTenantId(currentTenant()).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public BranchResponse addBranch(CreateBranchRequest request) {
        Barbershop barbershop = barbershopRepository.findByIdAndTenantId(request.barbershopId(), currentTenant())
                .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada"));

        Branch branch = new Branch();
        branch.setName(request.name());
        branch.setAddress(request.address());
        branch.setPhone(request.phone());
        branch.setBarbershop(barbershop);
        branch.setTenantId(currentTenant());

        barbershop.getBranches().add(branch);
        barbershopRepository.save(barbershop);
        return toResponse(branchRepository.save(branch));
    }

    @Transactional(readOnly = true)
    public List<BranchResponse> listBranches(UUID barbershopId) {
        return branchRepository.findByBarbershopIdAndTenantId(barbershopId, currentTenant()).stream()
                .map(this::toResponse)
                .toList();
    }

    private BarbershopResponse toResponse(Barbershop barbershop) {
        return new BarbershopResponse(
                barbershop.getId(),
                barbershop.getName(),
                barbershop.getDocument(),
                barbershop.getPhone(),
                barbershop.getEmail(),
                barbershop.getPlan()
        );
    }

    private BranchResponse toResponse(Branch branch) {
        UUID barbershopId = branch.getBarbershop() != null ? branch.getBarbershop().getId() : null;
        return new BranchResponse(branch.getId(), branch.getName(), branch.getAddress(), branch.getPhone(), barbershopId);
    }
}
