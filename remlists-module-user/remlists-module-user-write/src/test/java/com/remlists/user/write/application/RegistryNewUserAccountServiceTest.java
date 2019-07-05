package com.remlists.user.write.application;

import com.remlists.shared.domain.events.MessagePublisher;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.events.NewUserWasCreated;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.write.application.commands.RegistryUserCommand;
import com.remlists.user.write.application.services.impl.RegistryNewUserAccountServiceImpl;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.services.CreateRemlistsUserDomainService;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.ShortName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("ApplicationService")
@DisplayName("Application Service for create user tests")
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class RegistryNewUserAccountServiceTest {

    @Mock
    private CreateRemlistsUserDomainService domainService;

    @Mock
    private MessagePublisher publisher;

    @InjectMocks
    private RegistryNewUserAccountServiceImpl applicationService;

    @DisplayName("VALID TESTS")
    @Nested
    class User_ValidTests {

        @Test
        @DisplayName("Create valid user")
        void registryNewUser() {

            //Given
            Id id = new Id(UUID.randomUUID());
            EmailAddress email = new EmailAddress("dsc@g.com");
            ShortName shortName = new ShortName("dsc");
            Password password = new Password("pass");

            User newUser = new User(id, shortName, email, password);

            Mockito.when(domainService.createAValidUser(newUser)).thenReturn(newUser);

            //When
            applicationService.registryNewUserAccount(id, shortName, email, password);


            //Then
            Mockito.verify(domainService, Mockito.times(1)).createAValidUser(newUser);


        }


    }


}