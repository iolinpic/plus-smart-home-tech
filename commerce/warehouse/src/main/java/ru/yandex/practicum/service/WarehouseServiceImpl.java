package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.dto.AddressDto;
import ru.yandex.practicum.dto.BookedProductsDto;
import ru.yandex.practicum.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.dto.ShoppingCartDto;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    @Override
    public void addNewProductToWarehouse(NewProductInWarehouseRequest request) {

    }

    @Override
    public void increaseProductQuantity(AddProductToWarehouseRequest request) {

    }

    @Override
    public AddressDto getWarehouseAddress() {
        return null;
    }

    @Override
    public BookedProductsDto checkShoppingCart(ShoppingCartDto shoppingCart) {
        return null;
    }
}
