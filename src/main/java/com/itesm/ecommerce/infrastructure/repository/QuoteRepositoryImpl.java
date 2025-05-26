package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.domain.repository.QuoteRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public class QuoteRepositoryImpl implements QuoteRepository, PanacheRepositoryBase<Quote, Integer> {
    public Quote GetOrCreateQuoteForUser(){
        return null;
    }


}
