package ru.yandex.practicum.service;

import org.springframework.data.domain.Pageable;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;

import java.util.Collection;
import java.util.UUID;

public interface ShoppingService {
    ProductDto addProduct(ProductDto product);

    ProductDto findProductById(UUID id);

    ProductDto updateProduct(ProductDto product);

    void removeProductFromStore(UUID productId);

    void setProductQuantityState(SetProductQuantityStateRequest request);

    Collection<ProductDto> searchProducts(String category, Pageable params);
}
