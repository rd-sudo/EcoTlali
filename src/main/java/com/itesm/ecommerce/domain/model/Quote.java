package com.itesm.ecommerce.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quote {
    private int quote_id;
    private Customer customer;
    private LocalDateTime created_at;
    private BigDecimal total_amount;
    private Boolean installation;
    private List<QuoteItem> items;

}
