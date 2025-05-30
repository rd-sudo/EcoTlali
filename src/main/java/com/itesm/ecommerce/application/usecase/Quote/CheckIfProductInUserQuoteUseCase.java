package com.itesm.ecommerce.application.usecase.Quote;

import com.itesm.ecommerce.application.service.QuoteService;
import com.itesm.ecommerce.domain.model.QuoteItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CheckIfProductInUserQuoteUseCase {

    @Inject
    QuoteService quoteService;

    public QuoteItem execute(int user_id, int quoteId, int productId) {
        return quoteService.checkIfProductoInUserQuote(user_id, quoteId, productId);
    }
}
