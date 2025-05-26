package com.itesm.ecommerce.application.usecase.Quote;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddProductoToQuoteUseCase {
    public void execute(int quoteId, int productId, int quantity) {
        // Lógica para agregar un producto a la cotización
        // Puedes usar el repositorio de cotizaciones para buscar la cotización y agregar el producto
        // Por ejemplo:
        // Quote quote = quoteRepository.findById(quoteId);
        // Product product = productRepository.findById(productId);
        // quote.addProduct(product);
        // quoteRepository.save(quote);
    }
}
