package ru.yandex.practicum.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.dto.Pageable;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;

import java.util.Collection;
import java.util.UUID;

@FeignClient(name = "shopping-store", path = "/api/v1/shopping-store")
public interface ShoppingStoreOperations {

    @PutMapping
    ProductDto addProduct(@Valid @RequestBody ProductDto product);

    @GetMapping("/{productId}")
    ProductDto getProductById(@PathVariable UUID productId);

    @PostMapping
    ProductDto updateProduct(@Valid @RequestBody ProductDto product);

    @PostMapping("/removeProductFromStore")
    boolean removeProduct(@RequestBody UUID productId);

    @GetMapping
    Collection<ProductDto> searchProducts(String category, Pageable params);

    @PostMapping("/quantityState")
    boolean updateProductQuantity(@Valid @RequestBody SetProductQuantityStateRequest request);
}
