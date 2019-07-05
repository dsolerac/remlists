package com.remlists.user.write.integration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("IntegrationTest")
@DisplayName("Integration test for event system")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RaiseEventTest {

//    @Value("${spring.datasource.url}")
//    String url;

    private Logger LOG = LoggerFactory.getLogger(RaiseEventTest.class);


//    @Qualifier("redisTemplate")
//    @Autowired
//    private RedisTemplate redisTemplate;

//    @Autowired
//    private MessagePublisher redisPublisher;

//    private static RedisServer redisServer;


    @BeforeAll
    public static void startRedis() {


//        redisServer  = new RedisServer(6379);
//        redisServer.start();
//        System.out.println("Levanta REDIS -->" + redisServer);
    }

    @AfterAll
    public static void stopRedis() {
//        redisServer.stop();
    }


    @DisplayName("VALID TESTS")
    @Nested
    class RaiseEventTest_ValidTests {

        @Test
        @DisplayName("valid test")
        void valid() {

//            System.out.println("@@@@ -->" + url);

//            redisTemplate.convertAndSend("topic","mensaje de texto");


            //Given
//            String message = "Message " + UUID.randomUUID();
//            redisPublisher.publish(message);

            //When

            //Then
//            assertThat();


        }

    }

    @Disabled
    @DisplayName("FAILED TESTS")
    @Nested
    class RaiseEventTest_FailTests {

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