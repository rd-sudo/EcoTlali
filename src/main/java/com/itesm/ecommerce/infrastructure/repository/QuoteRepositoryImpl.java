package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.domain.repository.QuoteRepository;

import com.itesm.ecommerce.infrastructure.entity.QuoteEntity;
import com.itesm.ecommerce.infrastructure.mapper.QuoteMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class QuoteRepositoryImpl implements QuoteRepository, PanacheRepositoryBase<Quote, Integer> {
    public Quote GetOrCreateQuoteForUser(){
        return null;
    }


    public List<Quote> listUserQuotesByUserId(int userId) {
        List<QuoteEntity> entities = getEntityManager().createQuery(
                        "SELECT q FROM QuoteEntity q JOIN q.customer c JOIN c.user u WHERE u.id = :userId", QuoteEntity.class)
                .setParameter("userId", userId)
                .getResultList();

        // Mapea las entidades a modelos de dominio
        return entities.stream()
                .map(QuoteMapper::toModel)
                .toList();
    }
}
