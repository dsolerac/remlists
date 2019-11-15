package com.remlists.entrypoint.user.rest;

import com.remlists.entrypoint.RemlistWebLogin;
import com.remlists.entrypoint.controller.WebUrlMapper;
import com.remlists.entrypoint.controller.user.model.UserModel;
import com.remlists.entrypoint.controller.user.resource.UserResource;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Tag("IntegrationTest")
@DisplayName("Integration Rest layer for create new User")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateUserAccountRegistryRestTest {

    private Logger LOG = LoggerFactory.getLogger(CreateUserAccountRegistryRestTest.class);


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String authorizationToken;


    private final static String PROTOCOL = "http://";
    private final static String HOST = "localhost:";
    private final static String BASE_URL = PROTOCOL + HOST;
    private final static String USER_LOGIN_PATH = "/login";


    @BeforeEach
    public void login() throws Exception {


        RemlistWebLogin login = new RemlistWebLogin("user", "pass");
        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + port + USER_LOGIN_PATH, login, String.class);

        authorizationToken = response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        LOG.info("Authorization token --> " + authorizationToken);

    }


    @DisplayName("VALID TESTS")
    @Nested
    class CreateRemlistUserRestTest_ValidTests {

        @Test
        @DisplayName("Create a valid user without roles associated")
        void valid() {

            //Given
            UserModel model = new UserModel(UUID.randomUUID().toString(),
                                "ccm",
                                "ccm@g.com",
                                "pass");

            //When

            ResponseEntity<HttpEntity> response =  restTemplate.postForEntity(BASE_URL + port + "/api/users",
                                                                            model,
                                                                            HttpEntity.class);

            System.out.println("###  -->" + BASE_URL + port + "/api/users" + " -- " + response.getStatusCode());
            System.out.println("### headers -->" + BASE_URL + port + "/api/users" + " -- " + response.getHeaders());


            HttpEntity<UserResource> responseBody = response.getBody();

            System.out.println("### -->" + responseBody);

            //Then
//            assertThat();

        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class CreateRemlistUserRestTest_FailTests {

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
