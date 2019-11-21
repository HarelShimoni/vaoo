package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Return user id it exists")
    void testUserExists() throws Exception {
        UUID userId = UUID.randomUUID();
        userRepository.save((new User(userId,"tomer","ab","1232131","tomerabr@gmail.com","tel aviv","israel")));
        mockMvc.perform(get("/user/id/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",is("tomer")));

    }

    @Test
    @DisplayName("Throw exception is user  doesnt exist")
    void testUserIsMissing() {

    }
}