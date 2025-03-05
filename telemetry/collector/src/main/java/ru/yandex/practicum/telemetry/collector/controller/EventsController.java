package ru.yandex.practicum.telemetry.collector.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.telemetry.collector.models.SensorEvent;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventsController {

    @PostMapping("/sensors")
    public void sensors(@Valid @RequestBody SensorEvent event) {
        log.info(event.toString());
    }

    @PostMapping("/hubs")
    public void hubs(@RequestBody String event) {
        log.info(event);
    }
}
