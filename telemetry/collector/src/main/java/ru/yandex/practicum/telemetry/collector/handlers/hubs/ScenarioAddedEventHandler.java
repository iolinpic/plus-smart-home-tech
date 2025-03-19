package ru.yandex.practicum.telemetry.collector.handlers.hubs;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioAddedEventProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioConditionProto;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.telemetry.collector.kafka.KafkaClientProducer;
import ru.yandex.practicum.telemetry.collector.mappers.TimestampMapper;

@Component
@RequiredArgsConstructor
public class ScenarioAddedEventHandler implements HubEventHandler {
    final String topic = "telemetry.hubs.v1";
    private final KafkaClientProducer kafkaClientProducer;

    @Override
    public HubEventProto.PayloadCase getMessageType() {
        return HubEventProto.PayloadCase.SCENARIO_ADDED;
    }

    @Override
    public void handle(HubEventProto event) {
        kafkaClientProducer.getProducer().send(new ProducerRecord<>(topic, mapToAvro(event)));
    }

    private HubEventAvro mapToAvro(HubEventProto event) {
        ScenarioAddedEventProto eventProto = event.getScenarioAdded();
        ScenarioAddedEventAvro eventAvro = ScenarioAddedEventAvro.newBuilder()
                .setName(eventProto.getName())
                .setActions(eventProto.getActionList().stream().map(
                        deviceActionProto ->
                                DeviceActionAvro.newBuilder()
                                        .setType(ActionTypeAvro.valueOf(deviceActionProto.getType().name()))
                                        .setSensorId(deviceActionProto.getSensorId())
                                        .setValue(deviceActionProto.getValue())
                                        .build()

                ).toList())
                .setConditions(eventProto.getConditionList().stream().map(
                        conditionProto -> {
                            Object value = null;
                            if (conditionProto.getValueCase() == ScenarioConditionProto.ValueCase.INT_VALUE) {
                                value = conditionProto.getIntValue();
                            }
                            if (conditionProto.getValueCase() == ScenarioConditionProto.ValueCase.BOOL_VALUE) {
                                value = conditionProto.getBoolValue();
                            }
                            return ScenarioConditionAvro.newBuilder()
                                    .setSensorId(conditionProto.getSensorId())
                                    .setOperation(ConditionOperationAvro.valueOf(conditionProto.getOperation().name()))
                                    .setType(ConditionTypeAvro.valueOf(conditionProto.getType().name()))
                                    .setValue(value)
                                    .build();
                        }

                ).toList())
                .build();
        return HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(TimestampMapper.mapToInstant(event.getTimestamp()))
                .setPayload(eventAvro)
                .build();
    }
}
