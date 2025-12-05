package com.saas.barbearia.modules.pdv.api;

import com.saas.barbearia.modules.pdv.model.CashSummary;
import com.saas.barbearia.modules.pdv.service.CashService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/cash")
public class CashController {

    private final CashService service;

    public CashController(CashService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public CashSummary summary(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.summary(date != null ? date : LocalDate.now());
    }
}
