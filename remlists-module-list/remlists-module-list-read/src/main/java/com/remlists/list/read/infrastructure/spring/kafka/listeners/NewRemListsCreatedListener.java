package com.remlists.list.read.infrastructure.spring.kafka.listeners;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.remlists.list.read.application.commands.CreateNewRemListCommand;
import net.minidev.json.JSONArray;
import org.apache.kafka.common.protocol.types.Field;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class NewRemListsCreatedListener {

    private static final Logger LOG = LoggerFactory.getLogger(NewRemListsCreatedListener.class);


    private final KafkaTemplate kafkaTemplate;

    //TODO: una vez probado que funciona, hacer us puertro y su adaptador para ser inyectado.
    private CommandGateway commandGateway;

    public NewRemListsCreatedListener(KafkaTemplate kafkaTemplate,
                                      CommandGateway commandGateway) {
        this.kafkaTemplate = kafkaTemplate;
        this.commandGateway = commandGateway;
    }


    @KafkaListener(topics = "pruebatopic")
    public void processMessage(String jsonEvent) {

        LOG.info("####### NewRemListsCreatedListener.KafkaListener #########");
        LOG.debug("JSON event received -->" + jsonEvent);

        DocumentContext json = JsonPath.parse(jsonEvent);

        JSONArray nameArray = json.read("$..attributes.*.name") ;
        String name = (String) Arrays.asList( nameArray.toArray() ).stream().findFirst().orElseThrow();
        LOG.debug("name extracted from JSON event received -->" + name);


        JSONArray uuidArray = json.read("$..attributes.*.uuid") ;
        String uuid = (String) Arrays.asList( uuidArray.toArray() ).stream().findFirst().orElseThrow();
        LOG.debug("id extracted from JSON event received -->" + uuid);



        CreateNewRemListCommand command = new CreateNewRemListCommand(UUID.fromString(uuid), name);

        commandGateway.send(command).join();


        /* TODO
         * PASOS:
         * 1.- Se recibe un JSON:API que tenemos que transformar a objeto.
         *      Es suficiente con recoger los atributos dentro del nodo Data por medio de JSON Path
         * 2.- Se crea un comando con los datos valiosos.
         *      Hay que inyectar el command bus de axon.
         * 3.- Se lanza al command bus para que se persista.
         *
         * */


    }

}
