package com.remlists.list.write.infrastructure.spring.kafka;

import com.remlists.shared.domain.events.Event;
import com.remlists.shared.domain.events.MessagePublisher;
import com.remlists.shared.infrastructure.converter.EventEntityToJsonEventConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Publisher.Kafka.listWriteKafkaMessagePublisher;

@Component(listWriteKafkaMessagePublisher)
public class ListWriteKafkaMessagePublisher<T extends Event> implements MessagePublisher<T> {

    private Logger LOG = LoggerFactory.getLogger(ListWriteKafkaMessagePublisher.class);


    private final KafkaTemplate kafkaTemplate;
    private EventEntityToJsonEventConverter eventEntityToJsonEventConverter;


    public ListWriteKafkaMessagePublisher(KafkaTemplate kafkaTemplate,
                                          EventEntityToJsonEventConverter eventEntityToJsonEventConverter) {

        this.kafkaTemplate = kafkaTemplate;
        this.eventEntityToJsonEventConverter = eventEntityToJsonEventConverter;
    }


    @Override
    public void publish(Event<T> event) {

//        TODO: Esta pieza es clave tiene que analizar el type del evento y publicarlo en tantos topics como
//        sea conveniente. No solo se debe publicar en su topic directo, sino también en los intermedios.

//        TODO: ver en la documentación los parámetros del send y si conviene hacer uso de ellos.

//        kafkaTemplate.setDefaultTopic("pruebatopic");
//        kafkaTemplate.sendDefault( eventEntityToJsonEventConverter.convertToJsonEvent(event) );

//        kafkaTemplate.getProducerFactory().createProducer().




        LOG.debug("Event ready to send -->" + event);
        String jsonEvent = eventEntityToJsonEventConverter.convertToJsonEvent(event);

        LOG.debug("Event ready to send in JSON format-->" + jsonEvent);


        kafkaTemplate.send("pruebatopic", jsonEvent);


    }

    private void createTopicIfNotExists(String name) {

    }
}
