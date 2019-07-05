package com.remlists.list.read.infrastructure.axon.handlers.commands;

import com.remlists.list.read.application.commands.CreateNewRemListCommand;
import com.remlists.list.read.application.services.CreateNewRemListService;
import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.shared.domain.valueObjects.Id;
import org.axonframework.commandhandling.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.CommandHandler.createNewRemlistCommandHandlerRead;

import javax.validation.Valid;

@Component(createNewRemlistCommandHandlerRead)
public class CreateNewRemlistCommandHandler implements com.remlists.shared.application.commands.CommandHandler<CreateNewRemListCommand> {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistCommandHandler.class);


    private CreateNewRemListService createNewRemListService;

    public CreateNewRemlistCommandHandler(CreateNewRemListService createNewRemListService) {
        this.createNewRemListService = createNewRemListService;
    }

    @CommandHandler
    @Override
    public void execute(@Valid CreateNewRemListCommand command) {

        LOG.info("####### CreateNewRemlistCommandHandler.CommandHandler #########");
        LOG.debug("command received -->" + command);

        Id id = new Id(command.getUuid());
        RemListName name = new RemListName(command.getName());

        createNewRemListService.createNewRemList(id, name);



        /*
        1.- Crea los value objects correspondientes a cada uno de los atributos de command
        2.- Se invoca al caso de uso que debe estar inyectado
        */

    }
}
