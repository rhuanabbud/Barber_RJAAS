package com.saas.barbearia.modules.pdv.model;

import java.math.BigDecimal;

public record CashSummary(BigDecimal total, BigDecimal cash, BigDecimal card, BigDecimal pix, int receipts) {
}
