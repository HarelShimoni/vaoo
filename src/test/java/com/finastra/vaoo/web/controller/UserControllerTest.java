package com.finastra.vaoo.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.domain.account.Status;
import com.finastra.vaoo.domain.account.source.BankSource;
import com.finastra.vaoo.domain.user.User;
import com.finastra.vaoo.repository.UserRepository;
import com.finastra.vaoo.web.mappers.user.UserMapper;
import com.finastra.vaoo.web.model.user.UserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserRepository userRepo;

    @Test
    @DisplayName("Return user id it exists")
    void testUserExists() throws Exception {
        UUID generatedId = createUserInDb();

        mockMvc.perform(get("/user/" + generatedId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",is("tomer")));
    }

    @Test
    @Disabled
    @DisplayName("Return not found response (400) in case user was not found in repository")
    void testUserIsMissing() throws Exception {
        UUID uuid = UUID.randomUUID();
        mockMvc.perform(get("/user/" + uuid))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().string("Entity was not found"));

    }

    @Test
    @Disabled
    @DisplayName("test create user")
    void createUser() throws Exception {
        //arrange
        UserDto userDto = UserDto.builder()
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
    @Disabled
    @DisplayName("test update user")
    void updateUser() throws Exception {
        //arrange
        User userInDb = createUserInDbForUpdate();

        UserDto userDto = userMapper.toDto(userInDb);

        userDto.setCity("Haifa");

        //act and assert
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city",is("Haifa")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",is("tomer"))); //ensure existing value not changed
    }

    @Test
    @Disabled
    @DisplayName("delete user")
    void deleteUser() throws Exception {
        //arrange
        UUID userId = createUserInDb();
        String uri = "/user/" + userId;

        //act
        mockMvc.perform(get(uri)).andExpect(status().isOk()); //check user was created
        mockMvc.perform(delete(uri)).andExpect(status().isOk()); //delete the user

        //assert
        mockMvc.perform(get(uri)).andExpect(status().isNotFound());
    }

    private UUID createUserInDb() {
        BankSource hsbc = BankSource.builder()
                .accountNumber("123")
                .bank("hsbc")
                .branch("1232")
                .build();

        User user = userRepo.save((User.builder()
                .firstName("tomer")
                .email("tomer@erewrwe.com")
                .accounts(Arrays.asList(new Account(0, hsbc, Status.NEW)))
                .phone("12312312")
                .city("neta")
                .country("israel")
                .build()));


        return user.getId();
    }

    private User createUserInDbForUpdate() {
        BankSource hsbc = BankSource.builder()
                .accountNumber("123")
                .bank("hsbc")
                .branch("1232")
                .build();

        User user = userRepo.save((User.builder()
                .firstName("tomer")
                .lastName("ab")
                .email("tomer@erewrwe.com")
                .accounts(Arrays.asList(new Account(0, hsbc, Status.NEW)))
                .phone("12312312")
                .city("neta")
                .country("israel")
                .build()));


        return user;
    }



}