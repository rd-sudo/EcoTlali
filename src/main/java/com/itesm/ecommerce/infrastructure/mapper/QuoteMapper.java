package com.itesm.ecommerce.infrastructure.mapper;

import com.itesm.ecommerce.domain.model.Customer;
import com.itesm.ecommerce.domain.model.Quote;
import com.itesm.ecommerce.infrastructure.entity.QuoteEntity;

import java.util.stream.Collectors;

public class QuoteMapper {
    public static Quote toModel(QuoteEntity entity) {
        if (entity == null) {
            return null;
        }

        Quote quote = new Quote();
        quote.setQuote_id(entity.getQuote_id());
        quote.setCustomer(CustomerMapper.toModel(entity.getCustomer()));
        quote.setCreated_at(entity.getCreatedAt());
        quote.setTotal_amount(entity.getTotalAmount());
        quote.setInstallation(entity.getInstallation());
        /*quote.setItems(entity.getItems() != null ? entity.getItems()
                .stream()
                .map(QuoteItemMapper::toModel)
                .collect(Collectors.toList()) : null);*/

        return quote;

    }

    public static QuoteEntity toEntity(Quote model) {
        if (model == null) {
            return null;
        }

        QuoteEntity entity = new QuoteEntity();
        entity.setQuote_id(model.getQuote_id());
        entity.setCustomer(CustomerMapper.toEntity(model.getCustomer()));
        entity.setCreatedAt(model.getCreated_at());
        entity.setTotalAmount(model.getTotal_amount());
        entity.setInstallation(model.getInstallation());
        /*entity.setItems(model.getItems() != null ? model.getItems()
                .stream()
                .map(QuoteItemMapper::toEntity)
                .collect(Collectors.toList()) : null);*/

        return entity;
    }

}
