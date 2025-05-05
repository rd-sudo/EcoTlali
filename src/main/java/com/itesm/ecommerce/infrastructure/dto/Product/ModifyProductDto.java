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
public class ModifyProductDto {
    private Integer product_id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private Integer category_id;
    private String uuid;

}
