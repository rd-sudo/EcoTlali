package com.itesm.ecommerce.infrastructure.entity;

import com.itesm.ecommerce.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "electricity_footprint", nullable = false)
    private BigDecimal electricityFootprint;

    @Column(name = "water_footprint", nullable = false)
    private BigDecimal waterFootprint;

    @Column(name = "gas_footprint", nullable = false)
    private int gasFootprint;
}