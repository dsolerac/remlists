package com.remlists.shared.domain.events;

public interface MessagePublisher<T extends Event> {


    //TODO: lo que se envía debe ser un objeto de tipo JSON:DATA, hay que ver como hacer esto bien.
    //Lo suyo sería crear un objeto message que contemple los atributos del formato del JSON y sepa
    //transformarse en en JSON para ser enviado por el topic.
    public void publish(Event<T> event);


}
