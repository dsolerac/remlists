package com.remlists.user.domain.valueObjects;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

@Tag("ValueObjectsTest")
@DisplayName("Value Object for role name")
public class RoleNameTest {

    private Logger LOG = LoggerFactory.getLogger(RoleNameTest.class);

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.ENGLISH);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @DisplayName("VALID TESTS")
    @Nested
    class RoleNameTest_ValidTests {

        @Test
        @DisplayName("valid role name with upper letters")
        void createNewRoleName_WithUpperLetters() {

            //Given
            String name = "REVIWER";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);

            assertThat(roleName.getRole()).isEqualTo(name);
            assertThat(violations.isEmpty()).isTrue();

        }


        @Test
        @DisplayName("valid role name with upper letters and underscores")
        void createNewRoleName_WithUpperLettersAndUnderscore() {

            //Given
            String name = "REVIWER_ROLE_";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);

            assertThat(roleName.getRole()).isEqualTo(name);
            assertThat(violations.isEmpty()).isTrue();

        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class RoleNameTest_FailTests {

        @Test
        @DisplayName("role name with lowercase letters")
        void createNewRoleName_withLowercaseLetters() {

            //Given
            String name = "ReviweR";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Uppercase letter and underscores are only allowed in a valid role name.");

        }

        @Test
        @DisplayName("null role name ")
        void createNewRoleName_WithNull() {

            //Given
            String name = null;

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role name can not be null or empty.");


        }

        @Test
        @DisplayName("empty role name ")
        void createNewRoleName_Empty() {

            //Given
            String name = "";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role name can not be null or empty.");

        }

        @Test
        @DisplayName("more than 20 characters in role name ")
        void createNewRoleName_withMoreThan20Characters() {

            //Given
            String name = "REVIWER_ROLE_REVIWER_ROLE_";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role name length must be between 3 and 20 characters.");


        }

        @Test
        @DisplayName("less than 3 characters in role name ")
        void createNewRoleName_withLessThan3Characters() {

            //Given
            String name = "R_";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role name length must be between 3 and 20 characters.");

        }

        @Test
        @DisplayName("Role name with characters not allowed ")
        void createNewRoleName_withCharactersNotAllowed() {

            //Given
            String name = "15568";

            //When
            RoleName roleName = new RoleName(name);

            //Then
            Set<ConstraintViolation<RoleName>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Uppercase letter and underscores are only allowed in a valid role name.");

        }

    }

}
