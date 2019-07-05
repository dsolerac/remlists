package com.remlists.user.write.infrastructure.axon.handlers.commands;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.application.commands.RegistryUserCommand;
import com.remlists.user.write.application.services.RegistryNewUserAccountService;
import org.axonframework.commandhandling.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.CommandHandler.createNewUserCommandHandlerWrite;

@Component(createNewUserCommandHandlerWrite)
public class RegistryUserCommandHandler implements com.remlists.shared.application.commands.CommandHandler<RegistryUserCommand> {

    private Logger LOG = LoggerFactory.getLogger(RegistryUserCommandHandler.class);


    private RegistryNewUserAccountService service;

    public RegistryUserCommandHandler(RegistryNewUserAccountService service) {
        this.service = service;
    }

    @CommandHandler
    @Override
    public void execute(@Valid RegistryUserCommand command) {

        LOG.debug("### WRITE command -->" + command);

        Id id = new Id(command.getUuid());
        ShortName name = new ShortName(command.getShortName());
        EmailAddress emailAddress = new EmailAddress(command.getEmailAddress());
        Password password = new Password(command.getPassword());

        service.registryNewUserAccount(id, name, emailAddress, password);




        //TODO: lo he hecho rápido para probar las dependencias, hay que revisar toda la tubería porque no está bien.


        /*
        1.- Crea los value objects correspondientes a cada uno de los atributos de command
        2.- Se invoca al caso de uso que debe estar inyectado
        */

    }
}
