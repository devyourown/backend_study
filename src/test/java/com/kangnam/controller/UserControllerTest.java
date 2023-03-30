package com.kangnam.controller;

import org.junit.jupiter.api.Test;

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