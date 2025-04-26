package com.itesm.ecommerce.infrastructure.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCartDto {
    //Deberia ser igual que con el FireBaseUser pero de moemneto vamos a pasarle le id del carrito
    private int idCart;


}
