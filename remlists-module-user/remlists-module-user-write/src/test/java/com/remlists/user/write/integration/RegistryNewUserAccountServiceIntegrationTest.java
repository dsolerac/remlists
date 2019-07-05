package com.remlists.user.write.integration;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.exceptions.EmailAddressAlreadyExistsException;
import com.remlists.user.domain.exceptions.ShortNameAlreadyExistsException;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.application.commands.RegistryUserCommand;
import com.remlists.user.write.application.services.RegistryNewUserAccountService;
import com.remlists.user.write.infrastructure.spring.BeanNames;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Tag("IntegrationTest")
@DisplayName("Integration test for create user")
@SpringBootTest
public final class RegistryNewUserAccountServiceIntegrationTest {


    @Autowired
    @Qualifier(BeanNames.Application.Service.registryNewUserAccountService)
    private RegistryNewUserAccountService registryNewUserAccountService;


    @Autowired
    @Qualifier("userWriteRepositoryJPA")
    private UserRepository<User, Id> userRepository;


    @BeforeEach
    void setUp() {

        Locale.setDefault(Locale.ENGLISH);
    }

    @DisplayName("VALID TESTS")
    @Nested
    class User_ValidTests {

        @Test
        @DisplayName("Register a valid user")
        public void registerValidUser() {


            //Given
            UUID uuid = UUID.randomUUID();
            String name = "dsc3";
            String email = "dsc3@g.com";
            String pass  = "pass";

            Id id = new Id(uuid);
            ShortName shortName         = new ShortName(name);
            EmailAddress emailAddress   = new EmailAddress(email);
            Password password = new Password(pass);

            User newUser                = new User(id, shortName, emailAddress, password);


            RegistryUserCommand command = new RegistryUserCommand(uuid, name, email, pass);

            //When
            registryNewUserAccountService.registryNewUserAccount(id, shortName, emailAddress, password);

            User persistedUser = (User) userRepository.findById( id ).get();

            Optional<User> persistedUser2 = userRepository.findByShortName( shortName );

            //Then
            assertThat(persistedUser).isEqualTo(newUser);
            assertThat(persistedUser2).contains(newUser);
        }

    }


    @DisplayName("FAILED TESTS")
    @Nested
    class User_FailTests {

        @Test
        @DisplayName("shortname already exists")
        public void registerUser_WithShortnameInUse() {

            //Given
            Id id1 = new Id(UUID.randomUUID());
            ShortName shortName1 = new ShortName("dsc8");
            EmailAddress address1 = new EmailAddress("dsc8@g.com");
            Password password1 = new Password("pass");

            User newUser1 = new User(id1, shortName1, address1, password1);

            Id id2 = new Id(UUID.randomUUID());
            ShortName shortName2 = new ShortName("dsc8");
            EmailAddress address2 = new EmailAddress("dsc9@g.com");
            Password password2 = new Password("pass");

            User newUser2 = new User(id2, shortName2, address2, password2);

            //When
            registryNewUserAccountService.registryNewUserAccount(id1, shortName1,address1, password1);

            //Then
            assertThatThrownBy(() -> {
                registryNewUserAccountService.registryNewUserAccount(id2, shortName2, address2, password2);
            })
                    .isInstanceOf(ShortNameAlreadyExistsException.class)
                    .hasMessageContaining("This shortname already exists in the system.");
        }

        @Test
        @DisplayName("email already exists")
        public void registerUser_WithEmailInUse() {

            //Given

            Id id1 = new Id(UUID.randomUUID());
            ShortName shortName1 = new ShortName("dsc1");
            EmailAddress address1 = new EmailAddress("dsc1@g.com");
            Password password1 = new Password("pass");


            User newUser1 = new User(id1, shortName1, address1, password1);

            Id id2 = new Id(UUID.randomUUID());
            ShortName shortName2 = new ShortName("dsc2");
            EmailAddress address2 = new EmailAddress("dsc1@g.com");
            Password password2 = new Password("pass");

            User newUser2 = new User(id2, shortName2, address2, password2);

            //When
            registryNewUserAccountService.registryNewUserAccount(id1, shortName1,address1, password1);


            //Then
            assertThatThrownBy(() -> {
                registryNewUserAccountService.registryNewUserAccount(id2, shortName2, address2, password2);
            })
                    .isInstanceOf(EmailAddressAlreadyExistsException.class)
                    .hasMessageContaining("This email address already exists in the system");

        }

        @Test
        @DisplayName("not valid email format")
        public void registerUser_WithEmailNotValidFormat() {

            //Given
            Id id1 = new Id(UUID.randomUUID());
            ShortName shortName1 = new ShortName("dsc1");
            EmailAddress address1 = new EmailAddress("dsc1.g.com");
            Password password1 = new Password("pass");

            User newUser1 = new User(id1, shortName1, address1, password1);

            //When

            //Then
            assertThatThrownBy(() -> {
                registryNewUserAccountService.registryNewUserAccount(id1, shortName1,address1, password1);
            })
                    .isInstanceOfAny(ConstraintViolationException.class, TransactionSystemException.class);

        }

        @Test
        @DisplayName("not valid shortname format")
        public void registerUser_WithShortNameNotValidFormat() {

            //Given

            Id id1 = new Id(UUID.randomUUID());
            ShortName shortName1 = new ShortName("ds@c1");
            EmailAddress address1 = new EmailAddress("dsc1@g.com");
            Password password1 = new Password("pass");

            User newUser1 = new User(id1, shortName1, address1, password1);

            //When


            //Then
            assertThatThrownBy(() -> {
                registryNewUserAccountService.registryNewUserAccount(id1, shortName1,address1, password1);
            })
                    .isInstanceOfAny(ConstraintViolationException.class, TransactionSystemException.class);

        }
    }




}
