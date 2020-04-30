package com.april.beer.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationPasswordNotValid() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Tom")
                                .param("lastName", "Tomson")
                                .param("email", "tom@gmail.com")
                                .param("password", "tom1")
                                .param("confirmPassword", "tom1")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "password", "confirmPassword"))
                .andExpect(status().isOk());
    }
}
