package com.saas.barbearia.tenant;

import java.util.Optional;
import java.util.UUID;

public final class TenantContext {

    private static final ThreadLocal<UUID> CURRENT_TENANT = new ThreadLocal<>();
    private static final ThreadLocal<String> CURRENT_USER = new ThreadLocal<>();

    private TenantContext() {
    }

    public static void setTenant(UUID tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    public static Optional<UUID> getTenant() {
        return Optional.ofNullable(CURRENT_TENANT.get());
    }

    public static UUID requireTenant() {
        return getTenant().orElseThrow(() -> new TenantNotSetException("Tenant não informado"));
    }

    public static void setUser(String userId) {
        CURRENT_USER.set(userId);
    }

    public static Optional<String> getUser() {
        return Optional.ofNullable(CURRENT_USER.get());
    }

    public static void clear() {
        CURRENT_TENANT.remove();
        CURRENT_USER.remove();
    }
}
