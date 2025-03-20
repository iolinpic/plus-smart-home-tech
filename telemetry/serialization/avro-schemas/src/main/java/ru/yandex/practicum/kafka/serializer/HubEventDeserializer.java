package ru.yandex.practicum.kafka.serializer;

import org.apache.avro.Schema;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

public class HubEventDeserializer extends BaseAvroDeserializer<HubEventAvro>{
    public HubEventDeserializer(Schema schema) {
        super(schema);
    }
}
