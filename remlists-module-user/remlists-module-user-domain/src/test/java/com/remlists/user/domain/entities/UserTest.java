package com.remlists.user.domain.entities;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.objectMother.RoleObjectMother;
import com.remlists.user.domain.objectMother.UserObjectMother;
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
        @DisplayName("Create Simple user")
        void createSimpleUser() {

            //Given
            ShortName name = UserObjectMother.createShortName();
            EmailAddress email = UserObjectMother.createEmailAddress();

            //When
            User user = UserObjectMother.createBasicUser().build();

            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertThat( violations.isEmpty() ).isTrue();

            assertThat(user.getShortName()).isEqualTo(name);
            assertThat(user.getEmail()).isEqualTo(email);

        }

        @Test
        @DisplayName("Compare two users are equal")
        void userSameValueAs() {

            //Given
            Role role = RoleObjectMother.createTestingRole();

            //When
            User user1 = UserObjectMother.createBasicUser().build();
            user1.addRole(role);
            User user2 = UserObjectMother.createBasicUser().build();
            user2.addRole(role);

            boolean isSame = user1.sameIdentityAs(user2);

            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(user2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(isSame).isTrue();

            assertThat(user1).isEqualToIgnoringGivenFields(user2, "id", "createdAt", "updatedAt");

        }

        @Test
        @DisplayName("Compare two users are equal with different value object")
        void sameValueAs_WithTwoDifferentsValueObjects() {

            //Given
            ShortName dsolerac = UserObjectMother.createShortName();
            EmailAddress dsc_g_com = UserObjectMother.createEmailAddress();
            Password _123456 = UserObjectMother.createPassword();
            User user1 = new User.UserBuilder( dsolerac, dsc_g_com, _123456 ).build();

            User user2 = UserObjectMother.createBasicUser().build();

            Role role1 = RoleObjectMother.createTestingRole();
            Role role2 = RoleObjectMother.createTestingRole();

            //When
            user1.addRole(role1);
            user2.addRole(role2);

            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(user2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user1).isEqualToIgnoringGivenFields(user2, "id", "createdAt", "updatedAt");

        }

        @Test
        @DisplayName("Compare that two user are different")
        void notSameValueAs() {

            //Given
            ShortName name2 = new ShortName("dsolerac2");
            EmailAddress email2 = new EmailAddress("dsc@gmail.com2");
            Password password2 = new Password("pass");
            Role role1 = RoleObjectMother.createTestingRole();

            Id idRole = new Id();
            RoleName roleName = new RoleName("TESTING_ROLE");
            Role role2 = new Role(idRole, roleName);

            //When
            User user1 = UserObjectMother.createBasicUser().build();
            user1.addRole(role1);

            User user2 = new User.UserBuilder( name2, email2,password2).build();
            user2.addRole(role2);


            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(user2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(user1.sameIdentityAs(user2)).isFalse();
        }

        @Test
        @DisplayName("Relating a role with an user")
        void relateUserWithRole() {

            //Given
            Role role = RoleObjectMother.createTestingRole();
            User user = UserObjectMother.createBasicUser().build();

            //When
            user.addRole(role);

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
            EmailAddress email = UserObjectMother.createNotValidEmailAddress();
            ShortName shortName = UserObjectMother.createNotValidShortName();
            Password password = UserObjectMother.createNotValidPassword();


            //When
            User user = new User.UserBuilder(shortName, email,password).build();

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
            User user = new User.UserBuilder(id, name, email, password).build();
            user.removeRole(Role.createBasicUSerRole());

            //Then
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            LOG.info("Create an user without roles can not be possible: " + violations);
            System.out.println("Create an user without roles can not be possible: " + violations);
            assertThat(violations.isEmpty()).isFalse();


        }

    }

}

