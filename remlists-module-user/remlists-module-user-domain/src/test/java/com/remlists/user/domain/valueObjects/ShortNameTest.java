package com.remlists.user.domain.valueObjects;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("ValueObjects")
@DisplayName("Create shortname Value Object tests")
@ExtendWith(SpringExtension.class)
class ShortNameTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.ENGLISH);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("VALID TESTS")
    @Nested
    class ShortName_ValidTests {

        @Test
        @DisplayName("Create shortname")
        void createShortName() {

            //Given
            String name = "dsolerac";

            //When
            ShortName shortname = new ShortName(name);

            //Then
            assertThat(shortname.getShortName()).isEqualTo(name);
        }

        @Test
        @DisplayName("Create a very small shortname")
        void createSmallShortName() {

            //Given
            String name = "dsc";

            //When
            ShortName shortname = new ShortName(name);

            //Then
            assertThat(shortname.getShortName()).isEqualTo(name);
        }

        @Test
        @DisplayName("Create a very small shortname")
        void createLargeShortName() {

            //Given
            String name = "dsolerac__dsolerac__";

            //When
            ShortName shortname = new ShortName(name);

            //Then
            assertThat(shortname.getShortName()).isEqualTo(name);
        }

        @Test
        @DisplayName("Create shortname with numbers")
        void createShortName_WithNumbers() {

            //Given
            String name = "d1sol21er2ac";

            //When
            ShortName shortname = new ShortName(name);

            //Then
            assertThat(shortname.getShortName()).isEqualTo(name);

        }

        @Test
        @DisplayName("Create shortname with dots")
        void createShortName_WithDots() {

            //Given
            String name = "daniel.solera";

            //When
            ShortName shortname = new ShortName(name);

            //Then
            assertThat(shortname.getShortName()).isEqualTo(name);

        }

        @Test
        @DisplayName("Create shortname with underscore")
        void createShortName_WithUnderscore() {

            //Given
            String name = "daniel_solera";

            //When
            ShortName shortname = new ShortName(name);

            //Then
            assertThat(shortname.getShortName()).isEqualTo(name);

        }

        @Test
        @DisplayName("Compare two ShortName are equal")
        void sameValueAs() {

            //Given
            String name = "daniel_solera";

            //When
            ShortName shortName1 = new ShortName(name);
            ShortName shortName2 = new ShortName(name);

            //Then
            assertThat(shortName1.sameValueAs(shortName2)).isTrue();
        }

        @Test
        @DisplayName("Compare two ShortNames are different")
        void notSameValueAs() {

            //Given
            String name1 = "daniel_solera1";
            String name2 = "daniel_solera2";

            //When
            ShortName shortName1 = new ShortName(name1);
            ShortName shortName2 = new ShortName(name2);

            //Then
            assertThat(shortName1.sameValueAs(shortName2)).isFalse();
        }


    }

    @DisplayName("FAILED TESTS")
    @Nested
    class ShortName_FailTests {

        @Test
        @DisplayName("Null ShortName")
        void createShortName_WithNull() {

            //Given
            String name = null;

            //When
            ShortName shortName = new ShortName(name);

            //Then
            Set<ConstraintViolation<ShortName>> violations = validator.validate(shortName);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("ShortName can not be null or empty.");


        }

        @Test
        @DisplayName("Empty ShortName")
        void createShortName_WithEmpty() {

            //Given

            String name = "";

            //When
            ShortName shortName = new ShortName(name);

            //Then
            Set<ConstraintViolation<ShortName>> violations = validator.validate(shortName);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("ShortName can not be null or empty.");

        }

        @Test
        @DisplayName("ShortName less than 3")
        void createShortName_WithLengthLessThan3() {

            //Given

            String name = "ds";

            //When
            ShortName shortName = new ShortName(name);

            //Then
            Set<ConstraintViolation<ShortName>> violations = validator.validate(shortName);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("ShortName length must be between 3 and 20 characters.");


        }

        @Test
        @DisplayName("ShortName greather than 20")
        void createShortName_WithLengthGreaterThan20() {

            //Given

            String name = "dsolerac__dsolerac__dsolerac__";

            //When
            ShortName shortName = new ShortName(name);

            //Then
            Set<ConstraintViolation<ShortName>> violations = validator.validate(shortName);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("ShortName length must be between 3 and 20 characters.");


        }


        @Test
        @DisplayName("Not valid format email")
        void createShortName_WithNotValidFormat_at() {

            //Given
            String name = "dsol@erac";

            //When
            ShortName shortName = new ShortName(name);

            //Then
            Set<ConstraintViolation<ShortName>> violations = validator.validate(shortName);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("This ShortName does not have a valid format.");

        }


        @Test
        @DisplayName("Create email with other characters")
        void createShortName_WithNotValidFormat_withOtherCharacters() {

            //Given

            String name = "ds/o{lera}";

            //When
            ShortName shortName = new ShortName(name);

            //Then
            Set<ConstraintViolation<ShortName>> violations = validator.validate(shortName);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("This ShortName does not have a valid format.");


        }

    }

}