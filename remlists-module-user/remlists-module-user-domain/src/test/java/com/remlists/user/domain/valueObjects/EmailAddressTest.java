package com.remlists.user.domain.valueObjects;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.*;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("ValueObjects")
@DisplayName("Create email Value Object tests")
class EmailAddressTest {

    private Validator validator;

    @BeforeEach
    void setUp() {

        Locale.setDefault(Locale.ENGLISH);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("VALID TESTS")
    @Nested
    class EmailAddres_ValidTests {

        @Test
        @DisplayName("Create email")
        void createEmail() {

            //Given
            String email = "dsolerac@gmail.com";

            //When
            EmailAddress emailAdd = new EmailAddress(email);

            //Then
            assertThat(emailAdd.getEmailAddress()).isEqualTo(email);


        }

        @Test
        @DisplayName("Create email with numbers")
        void createEmail_WithNumbers() {

            //Given

            String email = "ds12ole34rac@gm56ail.c4om";

            //When

            EmailAddress emailAdd = new EmailAddress(email);

            //Then
            assertThat(emailAdd.getEmailAddress()).isEqualTo(email);

        }

        @Test
        @DisplayName("Create email with dots")
        void createEmail_WithDots() {

            //Given

            String email = "daniel.solera@gmail.solera.com";

            //When

            EmailAddress emailAdd = new EmailAddress(email);

            //Then
            assertThat(emailAdd.getEmailAddress()).isEqualTo(email);

        }

        @Test
        @DisplayName("Create email with underscore")
        void createEmail_WithUnderscore() {

            //Given

            String email = "daniel_solera@gmail.com";

            //When

            EmailAddress emailAdd = new EmailAddress(email);

            //Then
            assertThat(emailAdd.getEmailAddress()).isEqualTo(email);

        }

        @Test
        @DisplayName("Compare two emails are equal")
        void sameValueAs() {

            //Given

            String email = "dsolerac@gmail.com";

            //When

            EmailAddress emailAdd1 = new EmailAddress(email);

            EmailAddress emailAdd2 = new EmailAddress(email);

            //Then
            assertThat(emailAdd1.sameValueAs(emailAdd2)).isTrue();
        }

        @Test
        @DisplayName("Compare two emails are different")
        void notSameValueAs() {

            //Given

            String email1 = "dsolerac1@gmail.com";
            String email2 = "dsolerac2@gmail.com";

            //When

            EmailAddress emailAdd1 = new EmailAddress(email1);

            EmailAddress emailAdd2 = new EmailAddress(email2);

            //Then
            assertThat(emailAdd1.sameValueAs(emailAdd2)).isFalse();
        }

        @DisplayName("Create valid email with not usual characters")
        @ParameterizedTest
        @ValueSource(strings = {"simple@example.com", "very.common@example.com", "disposable.style.email.with+symbol@example.com",
                "other.email-with-hyphen@example.com", "fully-qualified-Domain@example.com",
                "user.name+tag+sorting@example.com", "x@example.com", "example-indeed@strange-example.com", "admin@mailserver1",
                "example@s.example", "\" \"@example.org", "\"john..doe\"@example.org"})
        void createEmail_WithValidFormat_withNotUsualCharacters(String email) {

            //When
            EmailAddress emailAddress = new EmailAddress(email);


            //Then
            Set<ConstraintViolation<EmailAddress>> violations = validator.validate(emailAddress);
            assertThat(violations.isEmpty()).isTrue();


        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class EmailAddres_FailTests {

        @Test
        @DisplayName("Null email")
        void createEmail_WithNull() {

            //Given

            String email = null;

            //When
            EmailAddress emailAddress = new EmailAddress(email);

            //Then
            Set<ConstraintViolation<EmailAddress>> violations = validator.validate(emailAddress);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("Email address can not be null or empty.");


        }

        @Test
        @DisplayName("Empty email")
        void createEmail_WithEmpty() {

            //Given

            String email = "";

            //When
            EmailAddress emailAddress = new EmailAddress(email);

            //Then
            Set<ConstraintViolation<EmailAddress>> violations = validator.validate(emailAddress);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("Email address can not be null or empty.");

        }

        @Test
        @DisplayName("Email greater than 100")
        void createEmail_WithGreaterThan100() {

            //Given

            String email = "dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_dsolerac1_@gmail.com";

            //When
            EmailAddress emailAddress = new EmailAddress(email);

            //Then
            Set<ConstraintViolation<EmailAddress>> violations = validator.validate(emailAddress);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("This email address does not have a valid format.");


        }

        @Test
        @DisplayName("Not valid format email")
        void createEmail_WithNotValidFormat_at() {

            //Given

            String email = "dsol@erac@gmail.com";

            //When
            EmailAddress emailAddress = new EmailAddress(email);


            //Then
            Set<ConstraintViolation<EmailAddress>> violations = validator.validate(emailAddress);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("This email address does not have a valid format.");

        }

        @DisplayName("Create email with not allowed characters")
        @ParameterizedTest
        @ValueSource(strings = {"Abc.example.com", "A@b@c@example.com",
                "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com", "just\"not\"right@example.com",
                "this is\"not\\allowed@example.com", "this\\ still\\\"not\\\\allowed@example.com",
                "1234567890123456789012345678901234567890123456789012345678901234+x@example.com"})
        void createEmail_WithNotValidFormat_withOtherCharacters(String email) {

            //When
            EmailAddress emailAddress = new EmailAddress(email);


            //Then
            Set<ConstraintViolation<EmailAddress>> violations = validator.validate(emailAddress);

            assertThat(violations.isEmpty()).isFalse();
            assertThat(violations.stream().map(ConstraintViolation::getMessage).toArray()).contains("This email address does not have a valid format.");

        }

    }

}