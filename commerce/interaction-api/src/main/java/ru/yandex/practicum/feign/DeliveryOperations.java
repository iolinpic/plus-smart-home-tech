package ru.yandex.practicum.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.dto.CreateNewOrderRequest;
import ru.yandex.practicum.dto.DeliveryDto;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.dto.ProductReturnRequest;

@FeignClient(name = "delivery")
public interface DeliveryOperations {

    @PutMapping
    DeliveryDto create(@RequestBody DeliveryDto newDelivery);

    @PostMapping("/successful")
    void successful(@RequestBody String deliveryId);

    @PostMapping("/picked")
    void picked(@RequestBody String deliveryId);

    @PostMapping("/failed")
    void failed(@RequestBody String deliveryId);

    @PostMapping("/cost")
    double cost(@RequestBody OrderDto orderDto);

}
