package com.remlists.entrypoint.list.controller;

import com.remlists.entrypoint.controller.list.model.RemListModel;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Tag("IntegrationTest")
@DisplayName("Integration test for CreateNewRemlistController")
@SpringBootTest
@AutoConfigureMockMvc
public class CreateNewRemlistControllerTest {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("VALID TESTS")
    @Nested
    class CreateNewRemlistControllerTest_ValidTests {

        @Test
        @DisplayName("create a new remlist successfully")
        void createNewRemlistSuccessfully() throws Exception {

            //Given
            RemListModel model = new RemListModel("92153dec-81c2-44a7-83e7-da7ffc947521","Verduras");

            //When
            RequestBuilder request = MockMvcRequestBuilders
                    .post("/api/lists")
//                    .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(model.toJSON())
                    .accept(MediaType.APPLICATION_JSON);

            //Then
            MvcResult result = mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name.name", Matchers.is("Verduras")))
                    .andReturn();

        }


        @Test
        @DisplayName("create a new remlist exactly that one already exist")
        void remlistAlreadyExits() throws Exception {

//OJO con esto si ya existe no debe dejar crearlo, es una regla de negocio que debe ir en el domain service.

            //Given
            RemListModel model1 = new RemListModel("92153dec-81c2-44a7-83e7-da7ffc947521","Verduras");
            RemListModel model2 = new RemListModel("92153dec-81c2-44a7-83e7-da7ffc947521","Verduras");

            //When
            RequestBuilder request1 = MockMvcRequestBuilders
                    .post("/api/lists")
//                    .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(model1.toJSON())
                    .accept(MediaType.APPLICATION_JSON);

            mockMvc.perform(request1).andReturn();

            RequestBuilder request2 = MockMvcRequestBuilders
                    .post("/api/lists")
//                    .header(HttpHeaders.AUTHORIZATION, authorizationToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(model2.toJSON())
                    .accept(MediaType.APPLICATION_JSON);

            //Then
            MvcResult result = mockMvc.perform(request2)
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name.name", Matchers.is("Verduras")))
                    .andReturn();

            LOG.info("RESULT --> " + result.getResponse().getContentAsString());


        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class CreateNewRemlistControllerTest_FailTests {


    }

}
