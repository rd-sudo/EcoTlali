package com.itesm.ecommerce.domain.repository;

import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;

import java.util.List;

public interface QuoteRepository {
    Quote GetOrCreateQuoteForUser();
    List<Quote> listUserQuotesByUserId(int userId);
}
