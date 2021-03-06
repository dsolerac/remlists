package com.remlists.list.read.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

@Tag("IntegrationTest|ApplicationServiceTest|EntityTest|DomainServiceTest|ValueObjectsTest")
@DisplayName("Integration |Application Service |Entity |Domain Service |Value Object for .....")
@SpringBootTest
public class CreateNewRemlistReadTest {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistReadTest.class);


    @DisplayName("VALID TESTS")
    @Nested
    class CreateNewRemlistReadTest_ValidTests {

        @Test
        @DisplayName("valid test")
        void valid() {

            //Given

            //When

            //Then
//            assertThat();

        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class CreateNewRemlistReadTest_FailTests {

        @Test
        @DisplayName("fail test")
        void fail() {

            //Given

            //When

            //Then
//            assertThat();
        }
    }

}
