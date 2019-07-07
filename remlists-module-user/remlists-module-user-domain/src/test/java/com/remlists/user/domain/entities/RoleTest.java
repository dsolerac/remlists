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

@Tag("EntityTest")
@DisplayName("Create role entity tests")
public class RoleTest {

    private Logger LOG = LoggerFactory.getLogger(RoleTest.class);

    private Validator validator;

    @BeforeEach
    void setUp() {

        Locale.setDefault(Locale.ENGLISH);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @DisplayName("VALID TESTS")
    @Nested
    class RoleTest_ValidTests {

        @Test
        @DisplayName("Create a new role")
        void createNewRole() {

            //Given
            Id id = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            RoleDescription description = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");

            //When
            Role role = new Role(id, roleName);
            role.setDescription(description);

            //Then
            Set<ConstraintViolation<Role>> violations = validator.validate(role);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(role.getRole()).isEqualTo(roleName);
            assertThat(role.getDescription()).isEqualTo(description);

        }

        @Test
        @DisplayName("Compare two roles are equal")
        void sameValueAs() {

            //Given
            Id id = new Id(UUID.randomUUID());
            RoleName roleName = new RoleName("TESTING_ROLE");
            RoleDescription description = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");

            //When
            Role role1 = new Role(id, roleName);
            role1.setDescription(description);
            Role role2 = new Role(id, roleName);
            role2.setDescription(description);

            //Then
            Set<ConstraintViolation<Role>> violations = validator.validate(role1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(role2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(role1.equals(role2)).isTrue();
        }

        @Test
        @DisplayName("Compare two roles are equal with different value object")
        void sameValueAs_WithTwoDifferentsValueObjects() {

            //Given
            UUID uuid = UUID.randomUUID();

            Id id1 = new Id(uuid);
            Id id2 = new Id(uuid);
            RoleName name1 = new RoleName("TESTING_ROLE");
            RoleName name2 = new RoleName("TESTING_ROLE");
            RoleDescription description1 = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");
            RoleDescription description2 = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");

            //When
            Role role1 = new Role(id1, name1);
            role1.setDescription(description1);
            Role role2 = new Role(id2, name2);
            role2.setDescription(description2);

            //Then
            Set<ConstraintViolation<Role>> violations = validator.validate(role1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(role2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(role1.equals(role2)).isTrue();
        }

        @Test
        @DisplayName("Compare that two roles are different")
        void notSameValueAs() {

            //Given
            Id id1 = new Id(UUID.randomUUID());
            Id id2 = new Id(UUID.randomUUID());
            RoleName name1 = new RoleName("TESTING_ROLE");
            RoleName name2 = new RoleName("TESTING_ROLE");
            RoleDescription description1 = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");
            RoleDescription description2 = new RoleDescription("Es simplemente el texto de relleno de las imprentas y archivos de texto.");

            //When
            Role role1 = new Role(id1, name1);
            role1.setDescription(description1);
            Role role2 = new Role(id2, name2);
            role2.setDescription(description2);

            //Then
            Set<ConstraintViolation<Role>> violations = validator.validate(role1);
            assertThat(violations.isEmpty()).isTrue();
            violations = validator.validate(role2);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(role1.equals(role2)).isFalse();
        }

        @Test
        @DisplayName("to relate a role with an user")
        void relateRoleWithUser() {

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
            role.setUsers(Set.of(user));

            //Then
            Set<ConstraintViolation<Role>> violations = validator.validate(role);
            assertThat(violations.isEmpty()).isTrue();

            assertThat(role.getUsers()).contains(user);
        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class RoleTest_FailTests {

        @Test
        @DisplayName("fail test")
        void create() {

            //Given

            //When

            //Then
//            assertThat();
        }
    }

}
