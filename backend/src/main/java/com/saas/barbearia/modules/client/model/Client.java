package com.saas.barbearia.modules.client.model;

import com.saas.barbearia.modules.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends BaseEntity {

    @Column(nullable = false)
    private String name;
    private String email;
    private String phone;
    private int loyaltyPoints = 0;
}
