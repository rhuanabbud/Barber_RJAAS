package com.saas.barbearia.modules.common.model;

import com.saas.barbearia.tenant.TenantContext;
import jakarta.persistence.PrePersist;

public class TenantEntityListener {

    @PrePersist
    public void touchTenant(BaseEntity entity) {
        if (entity.getTenantId() == null) {
            entity.setTenantId(TenantContext.requireTenant());
        }
    }
}
