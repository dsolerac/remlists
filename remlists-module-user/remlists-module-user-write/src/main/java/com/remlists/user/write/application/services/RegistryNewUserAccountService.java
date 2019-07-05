package com.remlists.user.write.application.services;

import com.remlists.shared.application.commands.CommandHandler;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.application.commands.RegistryUserCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/*
Primary port
 */
@Validated
public interface RegistryNewUserAccountService {

    static final Logger LOG = LoggerFactory.getLogger(RegistryNewUserAccountService.class);


    void registryNewUserAccount(@Valid Id id,
                                @Valid ShortName shortName,
                                @Valid EmailAddress emailAddress,
                                @Valid Password password );


}
