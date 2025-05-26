package com.itesm.ecommerce.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartHasProduct {
    private Product product;
    @JsonIgnore
    private Quote cart;
    private int quantity;
}
