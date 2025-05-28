package com.itesm.ecommerce.infrastructure.dto.Quote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductToQuoteDTO {
    private int product_id;
    private int quote_id;
    private int quantity;
}
