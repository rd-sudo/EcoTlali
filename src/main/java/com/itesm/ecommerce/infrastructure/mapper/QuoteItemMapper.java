package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Product;
import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.domain.model.QuoteItem;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.infrastructure.entity.QuoteItemEntity;

public class QuoteItemMapper {
    public static QuoteItem toModel(QuoteItemEntity entity) {
        if (entity == null) {
            return null;
        }

        return new QuoteItem(
                entity.getQuoteItemId(),
                QuoteMapper.toModel(entity.getQuote()),
                ProductMapper.toModel(entity.getProduct()),
                entity.getQuantity()
        );
    }

    public static QuoteItemEntity toEntity(QuoteItem model) {
        if (model == null) {
            return null;
        }

        QuoteItemEntity entity = new QuoteItemEntity();
        entity.setQuoteItemId(model.getQuoteItemId());
        entity.setQuote(QuoteMapper.toEntity(model.getQuote()));
        entity.setProduct(ProductMapper.toEntity(model.getProduct()));
        entity.setQuantity(model.getQuantity());

        return entity;
    }
}
