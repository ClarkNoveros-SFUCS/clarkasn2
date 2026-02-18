package com.clarkasn2.clarkasn2.controllers;

import com.clarkasn2.clarkasn2.models.UserRepository;
import com.clarkasn2.clarkasn2.models.Users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
public class Clarkasn2ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepo;

    //Get request to index returns OK(200)
    @Test
    public void getIndex_return200_expect200() throws Exception{
        when(userRepo.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/users/index"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/index"))
            .andExpect(model().attributeExists("users"));
    }

    //missing name validation
    @Test 
    public void postCreate_missingName() throws Exception{
        mockMvc.perform(post("/users/create")
                .param("name", "")
                .param("email", "simonf@sfu.ca")
                .param("roleType", "PROF")
                .param("clarity", "5")
                .param("niceness", "5")
                .param("knowledgeableScore", "5"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/create"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("user", "name"));

    }

    //Invalid email validation
    @Test 
    public void postCreate_InvalidEmail() throws Exception{
        mockMvc.perform(post("/users/create")
                .param("name", "Simon Fraser")
                .param("email", "simonf_sfu+ca")
                .param("roleType", "PROF")
                .param("clarity", "5")
                .param("niceness", "5")
                .param("knowledgeableScore", "5"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/create"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("user", "email"));

    }

    //invalid niceness value validation
    @Test 
    public void postCreate_NicenessOutOfRange() throws Exception{
        mockMvc.perform(post("/users/create")
                .param("name", "Simon Fraser")
                .param("email", "simonf@sfu.ca")
                .param("roleType", "PROF")
                .param("clarity", "5")
                .param("niceness", "21")
                .param("knowledgeableScore", "5"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/create"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("user", "niceness"));
    }

    //Is user redirected to the view page corresponding to uid?
    @Test 
    public void postCreate_SuccessRedirect() throws Exception{
        when(userRepo.save(any(Users.class))).thenAnswer(invocation -> {
            Users saved = invocation.getArgument(0);
            saved.setUid(2);
            return saved;
        });

        mockMvc.perform(post("/users/create")
                .param("name", "Simon Fraser")
                .param("email", "simonf@sfu.ca")
                .param("roleType", "PROF")
                .param("clarity", "5")
                .param("niceness", "5")
                .param("knowledgeableScore", "5")
                .param("comment", "hello world"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/users/details/2"))
            .andExpect(flash().attributeExists("success"));
    }

    //is user stuck at create form when there is a missing field?
    @Test 
    public void postCreate_FormwithErrors() throws Exception{
        mockMvc.perform(post("/users/create")
                .param("name", "Simon Fraser")
                //missing email
                .param("roleType", "PROF")
                .param("clarity", "5")
                .param("niceness", "21")
                .param("knowledgeableScore", "5"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/create"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("user", "email"));
    }

}
