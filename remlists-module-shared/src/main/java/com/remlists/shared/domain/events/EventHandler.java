package com.remlists.shared.domain.events;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface EventHandler<T extends Event> {


    void receiveEvent(@Valid T event);

}
