package com.itesm.ecommerce.application.service;

import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.infrastructure.repository.QuoteItemRepositoryImpl;
import com.itesm.ecommerce.infrastructure.repository.QuoteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class QuoteService {

    @Inject
    QuoteItemRepositoryImpl quoteItemRepositoryImpl;

    @Inject
    QuoteRepositoryImpl quoteRepositoryImpl;


    public void createQuoteForUser(String userId, String productId, int quantity) {

    }

    public List<Quote> listUserQuotesByUserId(int userId) {
        return quoteRepositoryImpl.listUserQuotesByUserId(userId);
    }


    @PersistenceContext
    private EntityManager entityManager;

    public QuoteItem checkIfProductoInUserQuote(int user_id,int quoteId, int productId) {
        return quoteItemRepositoryImpl.checkIfProductoInUserQuote(user_id,quoteId, productId);

    }
    public void deleteQuote(String userId, String productId, int quantity) {

    }
    public void updateQuoteItem(QuoteItem quoteItem) {
        quoteItemRepositoryImpl.updateQuoteItem(quoteItem);
    }
    public void addProductToUserQuote(int user_id, int quoteId, int productId, int quantity) {
        quoteItemRepositoryImpl.addQuoteItem(quoteId, productId, quantity);

    }

}
