package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.infrastructure.repository.QuoteItemRepositoryImpl;
import com.itesm.ecommerce.infrastructure.repository.QuoteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class QuoteService {

    @Inject
    QuoteItemRepositoryImpl quoteItemRepositoryImpl;


    public void createQuoteForUser(String userId, String productId, int quantity) {

    }

    public void listQuotesByUser(String userId, String productId, int quantity) {

    }

    @PersistenceContext
    private EntityManager entityManager;

    public QuoteItem checkIfProductoInQuote(int quoteId, int productId) {
        return quoteItemRepositoryImpl.checkIfProductoInQuote(quoteId, productId);

    }
    public void deleteQuote(String userId, String productId, int quantity) {

    }
    public void updateQuoteItem(QuoteItem quoteItem) {
        quoteItemRepositoryImpl.updateQuoteItem(quoteItem);
    }
    public void addProductToQuote() {

    }
}
