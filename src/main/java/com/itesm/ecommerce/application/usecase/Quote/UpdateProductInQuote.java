package com.itesm.ecommerce.application.usecase.Quote;


import com.itesm.ecommerce.application.service.QuoteService;
import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.infrastructure.repository.QuoteItemRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class UpdateProductInQuote {

    @Inject
    QuoteService quoteService;
    public void execute(QuoteItem quoteitem) {
        quoteService.updateQuoteItem(quoteitem);
    }
}
