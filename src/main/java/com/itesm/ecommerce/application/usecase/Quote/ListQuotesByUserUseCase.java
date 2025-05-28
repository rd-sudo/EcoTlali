package com.itesm.ecommerce.application.usecase.Quote;

import com.itesm.ecommerce.domain.model.Quote;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ListQuotesByUserUseCase {
    public List<Quote> execute(String userId) {
        return null;
    }
}
