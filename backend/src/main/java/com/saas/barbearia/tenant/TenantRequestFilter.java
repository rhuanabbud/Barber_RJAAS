package com.saas.barbearia.tenant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TenantRequestFilter extends OncePerRequestFilter {

    @Value("${security.tenant-header:X-Tenant-Id}")
    private String tenantHeader;

    @Value("${security.auth-header:Authorization}")
    private String authHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String tenantValue = request.getHeader(tenantHeader);
            if (tenantValue != null && !tenantValue.isBlank()) {
                try {
                    UUID tenantId = UUID.fromString(tenantValue.trim());
                    TenantContext.setTenant(tenantId);
                    MDC.put("tenantId", tenantId.toString());
                } catch (IllegalArgumentException ex) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tenant inválido");
                    return;
                }
            }

            String userHeader = request.getHeader(authHeader);
            if (userHeader != null && !userHeader.isBlank()) {
                TenantContext.setUser(userHeader.replace("Bearer", "").trim());
                MDC.put("userId", TenantContext.getUser().orElse("anonymous"));
            }

            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
            MDC.clear();
        }
    }
}
