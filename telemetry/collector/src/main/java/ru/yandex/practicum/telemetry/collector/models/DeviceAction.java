package ru.yandex.practicum.telemetry.collector.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeviceAction {
    private String sensorId;
    private DeviceActionType type;
    private int value;
}
