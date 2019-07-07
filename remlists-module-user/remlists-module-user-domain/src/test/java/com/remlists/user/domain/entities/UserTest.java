package com.remlists.user.domain.entities;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("entity")
@DisplayName("Create user entity tests")
class UserTest {

    private Logger LOG = LoggerFactory.getLogger(UserTest.class);


    private Validator validator;

    @BeforeEach
    void setUp() {

        Locale.setDefault(Locale.ENGLISH);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("VALID TESTS")
    @Nested
    class User_ValidTests {

        @Test
        @DisplayName("Create user")
        void createUser() {

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName name = new ShortName("dsolerac");
            EmailAddress email = new EmailAddress("dsc@gmail.com");
            Password password = new Password("pass");

            Id idRole = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            Role role = new Role(idRole, roleName);


            //When
            User user = new User(id, name, email, password);
            user.setArrayRoles(role);

            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user.getShortName()).isEqualTo(name);
            assertThat(user.getEmail()).isEqualTo(email);

        }

        @Test
        @DisplayName("Compare two users are equal")
        void sameValueAs() {

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName name = new ShortName("dsolerac");
            EmailAddress email = new EmailAddress("dsc@gmail.com");
            Password password = new Password("pass");


            Id idRole = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            Role role = new Role(idRole, roleName);

            //When
            User user1 = new User(id, name, email, password);
            user1.setArrayRoles(role);
            User user2 = new User(id, name, email, password);
            user2.setArrayRoles(role);


            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(user2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user1).isEqualToIgnoringGivenFields(user2, "createdAt", "updatedAt");

        }

        @Test
        @DisplayName("Compare two users are equal with different value object")
        void sameValueAs_WithTwoDifferentsValueObjects() {

            //Given
            Id id = new Id(UUID.randomUUID());

            ShortName name1 = new ShortName("dsolerac");
            EmailAddress email1 = new EmailAddress("dsc@gmail.com");
            Password password1 = new Password("pass");


            ShortName name2 = new ShortName("dsolerac");
            EmailAddress email2 = new EmailAddress("dsc@gmail.com");
            Password password2 = new Password("pass");


            Id idRole = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            Role role1 = new Role(idRole, roleName);
            Role role2 = new Role(idRole, roleName);


            //When
            User user1 = new User(id, name1, email1,password1);
            user1.setArrayRoles(role1);

            User user2 = new User(id, name2, email2, password2);
            user2.setArrayRoles(role2);


            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(user2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user1).isEqualToIgnoringGivenFields(user2, "createdAt", "updatedAt");

        }

        @Test
        @DisplayName("Compare that two user are different")
        void notSameValueAs() {

            //Given
            ShortName name1 = new ShortName("dsolerac");
            ShortName name2 = new ShortName("dsolerac2");
            EmailAddress email1 = new EmailAddress("dsc@gmail.com");
            EmailAddress email2 = new EmailAddress("dsc@gmail.com2");
            Password password1 = new Password("pass");
            Password password2 = new Password("pass");


            Id idRole = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            Role role1 = new Role(idRole, roleName);
            Role role2 = new Role(idRole, roleName);

            //When
            User user1 = new User(new Id(UUID.randomUUID()), name1, email1,password1);
            user1.setArrayRoles(role1);
            User user2 = new User(new Id(UUID.randomUUID()), name2, email2,password2);
            user2.setArrayRoles(role2);


            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(user2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user1.sameIdentityAs(user2)).isFalse();
        }

        @Test
        @DisplayName("to relate a role with an user")
        void relateUserWithRole() {

            //Given
            Id id = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            RoleDescription description = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");
            Role role = new Role(id, roleName);
            role.setDescription(description);

            Id idUser = new Id(UUID.randomUUID());
            ShortName name = new ShortName("dsolerac");
            EmailAddress email = new EmailAddress("dsc@g.com");
            Password password = new Password("pass");

            User user = new User(id, name, email,password);


            //When
            user.setArrayRoles(role);


            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user.getRoles()).contains(role);
        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class User_FailTests {

        @Test
        @DisplayName("User with not valid format email")
        void createUser_WithEmailNotValidFormat() {

            //Given
            EmailAddress email = new EmailAddress("dsol.erac.gmail.com");
            ShortName shortName = new ShortName("dsolerac#@");
            Password password = new Password("pass");


            //When
            User user = new User(new Id(UUID.randomUUID()), shortName, email,password);

            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertThat(violations.isEmpty()).isFalse();

        }


        @Test
        @DisplayName("create user without any role")
        void relateUserWithRole() {

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName name = new ShortName("dsolerac");
            EmailAddress email = new EmailAddress("dsc@g.com");
            Password password = new Password("pass");



            //When
            User user = new User(id, name, email, password);



            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            LOG.info("Create an user without roles can not be possible: " + violations);
            System.out.println("Create an user without roles can not be possible: " + violations);
            assertThat(violations.isEmpty()).isFalse();


        }


    }


}

