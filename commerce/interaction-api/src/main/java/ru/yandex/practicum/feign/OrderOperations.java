package ru.yandex.practicum.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.dto.CreateNewOrderRequest;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.dto.ProductReturnRequest;

@FeignClient(name = "order")
public interface OrderOperations {

    @GetMapping
    OrderDto get(@RequestParam String username);

    @PutMapping
    OrderDto create(@RequestBody CreateNewOrderRequest newOrder);

    @PostMapping("/return")
    OrderDto returnOrder(@RequestParam ProductReturnRequest productReturnRequest);

    @PostMapping("/payment")
    OrderDto payment(@RequestBody String orderId);

    @PostMapping("/payment/failed")
    OrderDto paymentFailed(@RequestBody String orderId);

    @PostMapping("/delivery")
    OrderDto delivery(@RequestBody String orderId);

    @PostMapping("/delivery/failed")
    OrderDto deliveryFailed(@RequestBody String orderId);

    @PostMapping("/completed")
    OrderDto completed(@RequestBody String orderId);

    @PostMapping("/calculate/total")
    OrderDto calculateTotal(@RequestBody String orderId);

    @PostMapping("/calculate/delivery")
    OrderDto calculateDelivery(@RequestBody String orderId);

    @PostMapping("/assembly")
    OrderDto assembly(@RequestBody String orderId);

    @PostMapping("/assembly/failed")
    OrderDto assemblyFailed(@RequestBody String orderId);
}
