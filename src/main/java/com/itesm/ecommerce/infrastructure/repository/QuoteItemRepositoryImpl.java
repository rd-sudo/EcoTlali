package com.itesm.ecommerce.infrastructure.repository;

import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.domain.repository.QuoteItemRepository;
import com.itesm.ecommerce.infrastructure.entity.QuoteItemEntity;
import com.itesm.ecommerce.infrastructure.mapper.QuoteItemMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class QuoteItemRepositoryImpl implements QuoteItemRepository, PanacheRepositoryBase<QuoteItemEntity, Integer> {
    @Inject
    EntityManager entityManager; // Inyección del EntityManager

    public void addQuoteItem(int quoteId, int productId, int quantity){

    };
    public void removeQuoteItem(int quoteId, int productId){

    };

    @Transactional
    public void updateQuoteItem(QuoteItem quoteItem){
        QuoteItemEntity quoteItemEntity = QuoteItemMapper.toEntity(quoteItem);
        // Usa el repositorio para actualizar la entidad
        entityManager.merge(quoteItemEntity);
    };

    public QuoteItem checkIfProductoInQuote(int quoteId, int productId) {
        // Usar JPQL para consultar si el `QuoteItemEntity` existe
        String query = "SELECT qi FROM QuoteItemEntity qi WHERE qi.quote.quote_id = :quoteId AND qi.product.id = :productId";
        List<QuoteItemEntity> results = entityManager.createQuery(query, QuoteItemEntity.class)
                .setParameter("quoteId", quoteId)
                .setParameter("productId", productId)
                .getResultList(); // Obtiene todos los resultados como lista

        // Si la lista es vacía, devuelve null; si no, transforma el primer resultado
        return results.isEmpty() ? null : QuoteItemMapper.toModel(results.get(0));
    }


    }