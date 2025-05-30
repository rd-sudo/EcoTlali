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

    public QuoteItem checkIfProductoInUserQuote(int user_id, int quoteId, int productId) {
        // Consulta JPQL para realizar los joins
        System.out.println("Consulta JPQL" + user_id + " " + quoteId + " " + productId);
        String query = """
        SELECT qi
        FROM QuoteItemEntity qi
        JOIN qi.quote q
        JOIN q.customer c
        JOIN c.user u
        WHERE q.quote_id = :quoteId AND qi.product.id = :productId AND u.id = :userId
    """;

        // Ejecutar la consulta
        List<QuoteItemEntity> results = entityManager.createQuery(query, QuoteItemEntity.class)
                .setParameter("quoteId", quoteId)
                .setParameter("productId", productId)
                .setParameter("userId", user_id)
                .getResultList();


        if (!results.isEmpty()) {
            return QuoteItemMapper.toModel(results.get(0));
        }

        // Verifica si la quote está relacionada con el usuario
        Long quoteCount = entityManager.createQuery(
                        "SELECT COUNT(q) FROM QuoteEntity q JOIN q.customer c JOIN c.user u WHERE q.quote_id = :quoteId AND u.id = :userId", Long.class)
                .setParameter("quoteId", quoteId)
                .setParameter("userId", user_id)
                .getSingleResult();

        if (quoteCount == 0) {
            throw new IllegalArgumentException("La cotización (quote) no existe o no está relacionada con el usuario");
        }

        Long productCount = entityManager.createQuery(
                        "SELECT COUNT(p) FROM ProductEntity p WHERE p.id = :productId", Long.class)
                .setParameter("productId", productId)
                .getSingleResult();

        if (productCount == 0) {
            throw new IllegalArgumentException("El producto no existe");
        }

        // Si ambos existen pero no hay relación, retorna null
        return null;
    }


    }