package com.remlists.entrypoint.controller.user;


import com.remlists.entrypoint.controller.user.model.UserModel;
import com.remlists.entrypoint.controller.user.resource.UserResource;
import com.remlists.entrypoint.controller.user.resource.UserResourceAssembler;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.application.commands.RegistryUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(value = "api/users")
public class UserAccountRegistryController {
    private Logger LOG = LoggerFactory.getLogger(UserAccountRegistryController.class);


    @Autowired
    private CommandGateway commandGateway;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<UserResource> signUp(@RequestBody @Valid UserModel userModel,
                                           HttpServletRequest req,
                                           HttpServletResponse resp) throws MalformedURLException {

        RegistryUserCommand command = new RegistryUserCommand(UUID.fromString(userModel.getUuid()),
                                                              userModel.getShortName(),
                                                              userModel.getEmailAddress(),
                                                              userModel.getPassword());

        commandGateway.send(command).join();

        User user = new User(new Id(command.getUuid()),
                             new ShortName(command.getShortName()),
                             new EmailAddress(command.getEmailAddress()),
                             new Password(userModel.getPassword()));

        UserResourceAssembler ura = new UserResourceAssembler(UserAccountRegistryController.class);
        ResponseEntity<UserResource> re = new ResponseEntity( ura.toResource( user ), HttpStatus.ACCEPTED );


        return re;


    }















/*    @GetMapping("/validatePathVariable/{id}")
    ResponseEntity<String> validatePathVariable(
            @PathVariable("id") @Min(5) int id) {
        return ResponseEntity.ok("valid");
    }*/


}
