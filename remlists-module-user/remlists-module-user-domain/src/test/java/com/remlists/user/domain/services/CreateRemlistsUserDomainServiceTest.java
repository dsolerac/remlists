package com.remlists.user.domain.services;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.exceptions.EmailAddressAlreadyExistsException;
import com.remlists.user.domain.exceptions.ShortNameAlreadyExistsException;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Tag("DomainService")
@DisplayName("Domain Service for create user tests")
@ExtendWith(MockitoExtension.class)
class CreateRemlistsUserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateRemlistsUserDomainService domainService;


    @DisplayName("VALID TESTS")
    @Nested
    class CreateRemlistsUserDomainService_ValidTests {

        //TODO: descomentar esto, solo está así por las pruebas debidas al fallo de spring data y los repositorios.



        @Test
        @DisplayName("Create valid user")
        void createAValidUser() {
            //Given
            Id id = new Id(UUID.randomUUID());
            EmailAddress email = new EmailAddress("dsc@g.com");
            ShortName shortName = new ShortName("dsc");
            Password pass = new Password("pass");

            User newUser = new User(id, shortName, email, pass);


            Mockito.when(userRepository.findByShortName(shortName)).thenReturn(Optional.empty());
            Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
            Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

            //When
            User persistedUser = domainService.createAValidUser(newUser);

            //Then
            Mockito.verify(userRepository, Mockito.times(1)).findByShortName(shortName);
            Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
            assertThat(persistedUser).isEqualTo(newUser);

        }

        @Test
        @DisplayName("Does not create user because shortname already exists")
        void createUser_WithShortNameAlreadyInUse() {

            //Given
            Id id = new Id(UUID.randomUUID());
            EmailAddress email = new EmailAddress("dsc@g.com");
            ShortName shortName = new ShortName("dsc");
            Password pass = new Password("pass");

            User newUser = new User(id, shortName, email, pass);


            Mockito.when(userRepository.findByShortName(shortName)).thenReturn(Optional.of(newUser));
            Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());


            //When
            assertThatThrownBy(() -> {
                domainService.createAValidUser(newUser);
            })
                    .isInstanceOf(ShortNameAlreadyExistsException.class)
                    .hasMessageContaining("This shortname already exists in the system.");

            //Then
            Mockito.verify(userRepository, Mockito.times(1)).findByShortName(shortName);
            Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
        }

        @Test
        @DisplayName("Does not create user because email already exists")
        void createUser_WithEmailAlreadyInUse() {


            //Given
            Id id = new Id(UUID.randomUUID());
            EmailAddress email = new EmailAddress("dsc@g.com");
            ShortName shortName = new ShortName("dsc");
            Password pass = new Password("pass");
            User newUser = new User(id, shortName, email, pass);


            Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(newUser));


            //When
            assertThatThrownBy(() -> {
                domainService.createAValidUser(newUser);
            })
                    .isInstanceOf(EmailAddressAlreadyExistsException.class)
                    .hasMessageContaining("This email address already exists in the system.");

            //Then
            Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);

        }



    }


    @DisplayName("FAILED TESTS")
    @Nested
    class CreateRemlistsUserDomainService_FailTests {

//        @Test
//        @DisplayName("fail test")
//        void fail() {
//
//            //Given
//
//            //When
//
//            //Then
////            assertThat();
//        }

    }


}