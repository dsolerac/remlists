package com.remlists.shared.infrastructure.converter;

import com.google.gson.Gson;
import com.remlists.shared.domain.events.Event;
import org.springframework.stereotype.Component;

@Component("jsonEventToEntityEventConverter")
public class JsonEventToEntityEventConverter<E extends Event>  {

    private Gson jsonConverter;

    public JsonEventToEntityEventConverter(Gson jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    public E convertToEntityEvent(String jsonEvent, Class<E> typeEvent) {

        return jsonConverter.fromJson(jsonEvent, typeEvent);

    }


}
