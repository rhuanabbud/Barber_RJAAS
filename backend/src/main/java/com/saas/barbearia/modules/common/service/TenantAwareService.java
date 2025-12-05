package com.saas.barbearia.modules.common.service;

import com.saas.barbearia.modules.common.model.BaseEntity;
import com.saas.barbearia.tenant.TenantContext;

import java.util.UUID;

public abstract class TenantAwareService {

    protected UUID currentTenant() {
        return TenantContext.requireTenant();
    }

    protected void ensureOwnership(BaseEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Registro não encontrado");
        }
        if (!currentTenant().equals(entity.getTenantId())) {
            throw new IllegalArgumentException("Registro pertence a outro tenant");
        }
    }
}
