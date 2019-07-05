package com.remlists.user.write.application.services.impl;

import com.remlists.shared.domain.events.MessagePublisher;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.events.NewUserWasCreated;
import com.remlists.user.domain.services.CreateRemlistsUserDomainService;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.application.services.RegistryNewUserAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Application.Service.registryNewUserAccountService;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Domain.Service.createUserDomainService;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Publisher.Kafka.userWriteKafkaMessagePublisher;



@Service(registryNewUserAccountService)
@Transactional
public class RegistryNewUserAccountServiceImpl implements RegistryNewUserAccountService {


    private CreateRemlistsUserDomainService domainService;
    private MessagePublisher publisher;
    private String hostName;

    public RegistryNewUserAccountServiceImpl(@Qualifier(createUserDomainService) CreateRemlistsUserDomainService domainService,
                                             @Qualifier(userWriteKafkaMessagePublisher) MessagePublisher publisher,
                                             @Value("${remlists.user.write.config.host.name}") String hostName

    ) {

        this.domainService = domainService;
        this.publisher = publisher;
        this.hostName = hostName;
    }


    @Override
    public void registryNewUserAccount(@Valid Id id,
                                       @Valid ShortName shortName,
                                       @Valid EmailAddress emailAddress,
                                       @Valid Password password ) {

//        1.- Crear el usuario
//        2.- Lanzar el evento
//        3.- Enviar email

        User user = new User(id, shortName, emailAddress, password);
        user = domainService.createAValidUser(user);

        NewUserWasCreated event = new NewUserWasCreated(user, hostName);
//        publisher.publish( event );

    }
}
