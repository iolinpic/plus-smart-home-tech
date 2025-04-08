package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.dto.Pageable;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.feign.ShoppingStoreOperations;
import ru.yandex.practicum.service.ShoppingService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/shopping-store")
public class ShoppingStoreController implements ShoppingStoreOperations {
    private final ShoppingService shoppingService;

    @Override
    public ProductDto addProduct(ProductDto product) {
        return shoppingService.addProduct(product);
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return shoppingService.findProductById(productId);
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        return shoppingService.updateProduct(product);
    }

    @Override
    public boolean removeProduct(UUID productId) {
        shoppingService.removeProductFromStore(productId);
        return true;
    }

    @Override
    public Collection<ProductDto> searchProducts(String category, Pageable params) {
        return shoppingService.searchProducts(category, params);
    }

    @Override
    public boolean updateProductQuantity(SetProductQuantityStateRequest request) {
        shoppingService.setProductQuantityState(request);
        return true;
    }
}
