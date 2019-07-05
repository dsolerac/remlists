package com.remlists.shared.infrastructure.converter;

import com.google.gson.Gson;
import com.remlists.shared.domain.events.Event;
import org.springframework.stereotype.Component;

@Component
public class EventEntityToJsonEventConverter<E extends Event>  {


    private Gson jsonConverter;

    public EventEntityToJsonEventConverter(Gson jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    public String convertToJsonEvent(E event) {
        return jsonConverter.toJson(event);
    }


}
