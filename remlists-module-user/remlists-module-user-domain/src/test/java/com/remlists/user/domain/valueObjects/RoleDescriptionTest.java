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
@DisplayName("Value Object for role description")
public class RoleDescriptionTest {

    private Logger LOG = LoggerFactory.getLogger(RoleDescriptionTest.class);

    private Validator validator;


    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.ENGLISH);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("VALID TESTS")
    @Nested
    class RoleDescriptionTest_ValidTests {

        @Test
        @DisplayName("create valid role description")
        void createAValidRoleDescription() {

            //Given
            String description = "Es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500...";

            //When
            RoleDescription roleDescription = new RoleDescription(description);

            //Then
            Set<ConstraintViolation<RoleDescription>> violations = validator.validate(roleDescription);

            assertThat(roleDescription.getDescription()).isEqualTo(description);
            assertThat(violations.isEmpty()).isTrue();

        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class RoleDescriptionTest_FailTests {

        @Test
        @DisplayName("more than 300 characters in role description ")
        void createNewRoleDescription_withMoreThan20Characters() {

            //Given
            String description = "es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum " +
                    "ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor " +
                    "(N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló " +
                    "de tal manera que logró hacer un libro de textos especimen.";


            //When
            RoleDescription roleDescription = new RoleDescription(description);

            //Then
            Set<ConstraintViolation<RoleDescription>> violations = validator.validate(roleDescription);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role description length must be between 3 and 20 characters.");


        }

        @Test
        @DisplayName("less than 3 characters in role description ")
        void createNewRoleDescription_withLessThan3Characters() {

            //Given
            String description = "e.";


            //When
            RoleDescription roleDescription = new RoleDescription(description);

            //Then
            Set<ConstraintViolation<RoleDescription>> violations = validator.validate(roleDescription);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role description length must be between 3 and 20 characters.");

        }

        @Test
        @DisplayName("null role description")
        void createNewRoleDescription_WithNull() {

            //Given
            String description = null;

            //When
            RoleDescription roleDescription = new RoleDescription(description);

            //Then
            Set<ConstraintViolation<RoleDescription>> violations = validator.validate(roleDescription);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role description can not be null or empty.");


        }

        @Test
        @DisplayName("empty role description")
        void createNewRoleDescription_Empty() {

            //Given
            String description = "";

            //When
            RoleDescription roleName = new RoleDescription(description);

            //Then
            Set<ConstraintViolation<RoleDescription>> violations = validator.validate(roleName);
            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray())
                    .contains("Role description can not be null or empty.");

        }
    }



}
