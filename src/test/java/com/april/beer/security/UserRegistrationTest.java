package com.april.beer.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
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

    @Test
    public void submitRegistrationWithEmptyValues() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "")
                                .param("lastName", "")
                                .param("email", "")
                                .param("password", "")
                                .param("confirmPassword", "")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "firstName", "lastName", "email", "password", "confirmPassword"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationWithEmailNotValid() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Tom")
                                .param("lastName", "Tomsonowski")
                                .param("email", "tomNNNNN")
                                .param("password", "abc12345")
                                .param("confirmPassword", "abc12345")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationOk() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Tom")
                                .param("lastName", "Tomson")
                                .param("email", "tom@gmail.com")
                                .param("password", "tom123456")
                                .param("confirmPassword", "tom123456")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/registration?success"));
    }
}
