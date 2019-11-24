package com.finastra.vaoo.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.repository.UserRepository;
import com.finastra.vaoo.web.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.springframework.web.util.NestedServletException;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepo;

//    @BeforeEach
//    public void beforeTest() {
//        MockMvc mockMvc = standaloneSetup(UserController.class)
//                .setHandlerExceptionResolvers(createExceptionResolver())
//                .build();
//    }

    @Test
    @DisplayName("Return user id it exists")
    void testUserExists() throws Exception {
        User user = userRepo.save((User.builder()
                .firstName("tomer")
                .email("tomer@erewrwe.com")
                .phone("12312312")
                .city("neta")
                .country("israel")
                .build()));
        UUID generatedId = user.getId();

        mockMvc.perform(get("/user/id/" + generatedId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",is("tomer")));

    }

    @Test
    @DisplayName("Throw exception is user  doesnt exist")
    void testUserIsMissing() {

    }

    @Test
    @DisplayName("test create user")
    void createUser() throws Exception {
        //arrange
        UserDto userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .firstName("tomer")
                .lastName("ab")
                .city("sda")
                .country("dasds")
                .email("sadas@dsada.com")
                .phone("123131231")
                .build();

        //act and assert
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("delete user")
    void deleteUser() throws Exception {
        //arrange
        UUID userId = createUser(UUID.randomUUID());
        String uri = "/user/id/" + userId;

        //act
        mockMvc.perform(get(uri)).andExpect(status().isOk()); //check user was created
        mockMvc.perform(delete(uri)).andExpect(status().isOk()); //delete the user

        //assert
        //mockMvc.perform(get(uri)).andExpect(status().isNotFound()); //to enable after exception controller branch is merged
        assertThatThrownBy(() -> mockMvc.perform(get(uri)).andReturn()).isExactlyInstanceOf(NestedServletException.class); //sloppy, temp solution
    }

    private UUID createUser(UUID userId) {
        User user = userRepo.save((User.builder()
                .firstName("tomer")
                .email("tomer@erewrwe.com")
                .phone("12312312")
                .city("neta")
                .country("israel")
                .build()));

        return user.getId();

    }


//    //to enable once "exception controller" branch is merged
//    private ExceptionHandlerExceptionResolver createExceptionResolver() {
//        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
//            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
//                Method method = new ExceptionHandlerMethodResolver(RestExceptionController.class).resolveMethod(exception);
//                return new ServletInvocableHandlerMethod(new RestExceptionController(), method);
//            }
//        };
//        exceptionResolver.afterPropertiesSet();
//        return exceptionResolver;
//    }
}