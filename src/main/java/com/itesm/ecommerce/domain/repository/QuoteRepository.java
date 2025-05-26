package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;

public interface QuoteRepository {
    Quote GetOrCreateQuoteForUser();

}
