package ru.yandex.practicum.telemetry.collector.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScenarioCondition {
    private String sensorId;
    private ScenarioCondition type;
    private ScenarioOperation operation;
    private int value;
}

