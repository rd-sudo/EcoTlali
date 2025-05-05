package com.itesm.ecommerce.infrastructure.dto.Product;

import com.itesm.ecommerce.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO
{
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private float price;

    public Product toProduct()
    {
        return new Product(name, description, price);
    }
}
