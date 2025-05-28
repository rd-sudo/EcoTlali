package com.itesm.ecommerce.application.usecase.Quote;

import com.itesm.ecommerce.application.service.QuoteService;
import com.itesm.ecommerce.domain.model.Quote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AddProductoToUserQuoteUseCase {
    @Inject
    QuoteService quotService;

    public void execute(int user_id, int quoteId, int productId, int quantity) {
        List<Quote> list = quotService.listUserQuotesByUserId(user_id);
        boolean exists = list.stream().anyMatch(q -> q.getQuote_id() == quoteId);
        if (!exists) {
            throw new IllegalArgumentException("La cotización no pertenece al usuario");
        }
        quotService.addProductToUserQuote(user_id, quoteId, productId, quantity);
    }
}
