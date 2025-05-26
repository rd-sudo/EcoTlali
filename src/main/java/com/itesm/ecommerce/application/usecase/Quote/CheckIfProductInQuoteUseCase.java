package com.itesm.ecommerce.application.usecase.Quote;

import com.itesm.ecommerce.application.service.QuoteService;
import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CheckIfProductInQuoteUseCase {

    @Inject
    QuoteService quoteService;

    public QuoteItem execute(int quoteId, int productId) {
        return quoteService.checkIfProductoInQuote(quoteId, productId);
    }
}
