package com.remlists.shared.domain.events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Event<T> implements Serializable {

    private Data data;
    private Map<String, String> meta;

    public Event(String eventType, String hostName, T entity) {

        data = new Data(eventType, entity);
        meta = new HashMap<>();
        meta.put("host", hostName);

    }

    public void addMetaAttribute(String key, String value){
        meta.put(key, value);
    }


    public Data getData() {
        return data;
    }

    public Map<String, String> getMeta() {
        return meta;
    }


    public class Data<T> {

        private UUID eventUuid;
        private String eventType;
        private LocalDateTime occurredOn;

        private T attributes;


        public Data(String eventType, T entity) {

            this.eventType = eventType;
            this.occurredOn = LocalDateTime.now();
            this.eventUuid = UUID.randomUUID();

            this.attributes = entity;

        }

        public UUID getEventUuid() {
            return eventUuid;
        }

        public String getEventType() {
            return eventType;
        }

        public LocalDateTime getOccurredOn() {
            return occurredOn;
        }


        @Override
        public String toString() {
            return "Data{" +
                    "eventUuid=" + eventUuid +
                    ", eventType='" + eventType + '\'' +
                    ", occurredOn=" + occurredOn +
                    ", attributes=" + attributes +
                    '}';
        }

    }


    @Override
    public String toString() {
        return "Event{" +
                "data=" + data +
                ", meta=" + meta +
                '}';
    }

}