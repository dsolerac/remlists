package com.remlists.list.write.infrastructure;

import com.remlists.list.write.application.commands.CreateNewRemListCommand;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Tag("IntegrationTest")
@DisplayName("Integration test for create list")
@SpringBootTest()
class CreateNewRemlistWriteTest {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistWriteTest.class);


    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    private SimpleCommandBus commandBus;


    @DisplayName("VALID TESTS")
    @Nested
    class CreateNewRemlistTest_ValidTests {

        @Test
        @DisplayName("valid test")
        void valid() {

            //Given
            CreateNewRemListCommand command = new CreateNewRemListCommand(UUID.randomUUID(), "Fruta");

            //When
            try {

                commandGateway.send(command).join();

            } catch (Exception e) {
                LOG.info("La excepción llega hasta el el lugar donde se lanza el comando", e);
                System.out.println("La excepción llega hasta el el lugar donde se lanza el comando");
                e.printStackTrace();
            }

            //Then
//            assertThat();
        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class CreateNewRemlistTest_FailTests {

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

