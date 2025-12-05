package com.saas.barbearia.modules.pdv.service;

import com.saas.barbearia.modules.common.service.TenantAwareService;
import com.saas.barbearia.modules.pdv.model.CashSummary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CashService extends TenantAwareService {

    public CashSummary summary(LocalDate date) {
        // Stub com valores fictícios. Integrações com vendas podem ser adicionadas aqui.
        BigDecimal total = BigDecimal.valueOf(1200.50);
        return new CashSummary(
                total,
                BigDecimal.valueOf(300),
                BigDecimal.valueOf(550.50),
                BigDecimal.valueOf(350),
                18
        );
    }
}
