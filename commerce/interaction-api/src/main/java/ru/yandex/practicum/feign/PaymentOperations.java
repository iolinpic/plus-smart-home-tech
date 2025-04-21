package ru.yandex.practicum.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.dto.OrderDto;
import ru.yandex.practicum.dto.PaymentDto;

@FeignClient(name = "payment")
public interface PaymentOperations {

    @PostMapping()
    PaymentDto payment(@RequestBody OrderDto orderDto);

    @PostMapping("/totalCost")
    double totalCost(@RequestBody OrderDto orderDto);

    @PostMapping("/refund")
    void refund(@RequestBody String paymentId);

    @PostMapping("/productCost")
    double productCost(@RequestBody OrderDto orderDto);

    @PostMapping("/failed")
    void failed(@RequestBody String paymentId);
}
