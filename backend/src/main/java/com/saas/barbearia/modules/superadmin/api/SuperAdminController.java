package com.saas.barbearia.modules.superadmin.api;

import com.saas.barbearia.modules.superadmin.service.SuperAdminService;
import com.saas.barbearia.modules.superadmin.service.SuperAdminSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/super-admin")
public class SuperAdminController {

    private final SuperAdminService service;

    public SuperAdminController(SuperAdminService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public SuperAdminSummary summary() {
        return service.buildSummary();
    }
}
