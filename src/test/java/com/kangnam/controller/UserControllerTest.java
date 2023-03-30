package com.kangnam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangnam.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void testLogin() throws Exception {
        MvcResult signupResult = createUser();
        String email = "hi@naver.com";
        String password = "1234";
        String body = mapper.writeValueAsString(makeUserDTO(email, password));
        mockMvc.perform(post("/signin")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private UserDTO makeUserDTO(String email, String password) {
        return UserDTO.builder()
                .email(email)
                .password(password)
                .build();
    }

    private MvcResult createUser() throws Exception {
        String email = "hi@naver.com";
        String password = "1234";
        String body = mapper.writeValueAsString(makeUserDTO(email, password));
        return mockMvc.perform(post("/signup")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}