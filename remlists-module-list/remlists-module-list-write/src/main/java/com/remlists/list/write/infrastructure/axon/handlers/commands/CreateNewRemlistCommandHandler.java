package com.remlists.list.write.infrastructure.axon.handlers.commands;

import com.remlists.list.write.application.commands.CreateNewRemListCommand;
import com.remlists.list.write.application.services.CreateNewRemListService;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.list.domain.valueObjects.RemListName;
import org.axonframework.commandhandling.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.CommandHandler.createNewRemlistCommandHandlerWrite;

@Component(createNewRemlistCommandHandlerWrite)
public class CreateNewRemlistCommandHandler implements com.remlists.shared.application.commands.CommandHandler<CreateNewRemListCommand> {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistCommandHandler.class);


    private CreateNewRemListService createNewRemListService;

    public CreateNewRemlistCommandHandler(CreateNewRemListService createNewRemListService) {
        this.createNewRemListService = createNewRemListService;
    }

    @CommandHandler
    @Override
    public void execute(@Valid CreateNewRemListCommand command) {

        LOG.debug("### WRITE command -->" + command);

        Id id = new Id(command.getUuid());
        RemListName name = new RemListName(command.getName());

        createNewRemListService.createNewRemList(id, name);



        /*
        1.- Crea los value objects correspondientes a cada uno de los atributos de command
        2.- Se invoca al caso de uso que debe estar inyectado
        */

    }
}
