package ru.yandex.practicum.telemetry.collector.handlers.sensors;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.LightSensorProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.kafka.KafkaClientProducer;
import ru.yandex.practicum.telemetry.collector.mappers.TimestampMapper;

@Component
@RequiredArgsConstructor
public class LightSensorEventHandler implements SensorEventHandler {
    private final String topic = "telemetry.sensors.v1";
    private final KafkaClientProducer kafkaClientProducer;

    @Override
    public SensorEventProto.PayloadCase getMessageType() {
        return SensorEventProto.PayloadCase.LIGHT_SENSOR_EVENT;
    }

    @Override
    public void handle(SensorEventProto event) {
        kafkaClientProducer.getProducer().send(new ProducerRecord<>(topic, mapToAvro(event)));
    }

    private SensorEventAvro mapToAvro(SensorEventProto event) {
        LightSensorProto sensorProto = event.getLightSensorEvent();
        LightSensorAvro sensorAvro = LightSensorAvro.newBuilder()
                .setLinkQuality(sensorProto.getLinkQuality())
                .setLuminosity(sensorProto.getLuminosity())
                .build();
        return SensorEventAvro.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(TimestampMapper.mapToInstant(event.getTimestamp()))
                .setPayload(sensorAvro)
                .build();
    }
}
