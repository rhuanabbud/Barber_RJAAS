package com.saas.barbearia.tenant;

public class TenantNotSetException extends RuntimeException {
    public TenantNotSetException(String message) {
        super(message);
    }
}
