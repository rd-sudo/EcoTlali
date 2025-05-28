package com.itesm.ecommerce.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quote_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_item_id", nullable = false)
    private int quoteItemId;  // Identificador único del elemento de la cotización

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quote_id", nullable = false)
    @JsonBackReference // Esto evitará serialización cíclica
    private QuoteEntity quote;  // Relación con la entidad "Quote" (muchos a uno)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;  // Relación con la entidad "Product" (muchos a uno)

    @Column(name = "quantity", nullable = false)
    private int quantity;  // Cantidad del producto agregado a la cotización

}
