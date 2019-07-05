package com.remlists.entrypoint.controller.list;

import com.remlists.entrypoint.controller.list.model.RemListModel;
import com.remlists.entrypoint.controller.list.resource.RemListResource;
import com.remlists.entrypoint.controller.list.resource.RemListsResourceAssembler;
import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.list.write.application.commands.CreateNewRemListCommand;
import com.remlists.shared.domain.valueObjects.Id;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(value = "/api/lists")
public class CreateNewRemlistController {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistController.class);


    @Autowired
    private CommandGateway commandGateway;

//https://www.baeldung.com/spring-boot-json


    @PostMapping
    public HttpEntity<RemListResource> createNewRemlist(@RequestBody @Valid RemListModel model,
                                                         HttpServletRequest req,
                                                         HttpServletResponse resp) throws NoSuchMethodException {

        CreateNewRemListCommand command = new CreateNewRemListCommand(UUID.fromString(model.getUuid()), model.getName());

        commandGateway.send(command).join();

        RemList remList = new RemList(new Id(command.getUuid()), new RemListName(command.getName()));

        RemListsResourceAssembler rra = new RemListsResourceAssembler();
        ResponseEntity<RemListResource> re = new ResponseEntity( rra.toResource( remList ), HttpStatus.CREATED );

        return re;

    }
}


