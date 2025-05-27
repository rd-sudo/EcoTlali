package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.QuoteItem;

public interface QuoteItemRepository {
    void addQuoteItem(int quoteId, int productId, int quantity);
    void removeQuoteItem(int quoteId, int productId);
    void updateQuoteItem(QuoteItem quoteItem);
    QuoteItem checkIfProductoInUserQuote(int user_id, int quoteId, int productId);
}
