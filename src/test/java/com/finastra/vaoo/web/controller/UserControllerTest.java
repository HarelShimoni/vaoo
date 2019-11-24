package com.finastra.vaoo.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.repository.UserRepository;
import com.finastra.vaoo.service.UserService;
import com.finastra.vaoo.web.model.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepo;

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
        UserDto userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .firstName("tomer")
                .lastName("ab")
                .city("sda")
                .country("dasds")
                .email("sadas@dsada.com")
                .phone("123131231")
                .build();

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

    }
}