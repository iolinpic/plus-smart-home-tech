package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.dto.Pageable;
import ru.yandex.practicum.dto.ProductCategory;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.ProductState;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.exceptions.ProductNotFoundException;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.repository.ShoppingStoreRepository;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {
    private final ShoppingStoreRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto addProduct(ProductDto product) {
        Product productDb = productMapper.mapToProduct(product);
        productDb = productRepository.save(productDb);
        return productMapper.mapToProductDto(productDb);
    }

    @Override
    public ProductDto findProductById(UUID id) {
        Product product = getProductFromStore(id);
        return productMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        getProductFromStore(product.getProductId());
        Product productUpdated = productMapper.mapToProduct(product);
        productUpdated = productRepository.save(productUpdated);
        return productMapper.mapToProductDto(productUpdated);
    }

    @Override
    public void removeProductFromStore(UUID productId) {
        Product product = getProductFromStore(productId);
        product.setProductState(ProductState.DEACTIVATE);
        productRepository.save(product);
    }

    @Override
    public void setProductQuantityState(SetProductQuantityStateRequest request) {
        Product product = getProductFromStore(request.getProductId());
        product.setQuantityState(request.getQuantityState());
        productRepository.save(product);
    }

    @Override
    public Collection<ProductDto> searchProducts(String category, Pageable params) {
        Sort sort = Sort.by(params.getSort().stream().map(Sort.Order::asc).toList());
        PageRequest pageable = PageRequest.of(params.getPage(), params.getSize(), sort);
        Collection<Product> products = productRepository.getProductsByProductCategory(ProductCategory.valueOf(category), pageable);
        return productMapper.mapToListProductDto(products.stream().toList());
    }

    private Product getProductFromStore(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product is not found"));
    }
}
