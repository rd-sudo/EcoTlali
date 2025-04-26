package com.itesm.ecommerce.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /* DESCOMENTA ESTO LEO
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user
     */

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartHasProductsEntity> cartHasProductsEntity;

    @Column(name = "status")
    private String status;

}
