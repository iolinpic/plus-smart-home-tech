package ru.yandex.practicum.telemetry.collector.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioRemovedEvent extends HubEvent {
    @NotEmpty
    @Size(min = 3)
    private String name;


    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}

