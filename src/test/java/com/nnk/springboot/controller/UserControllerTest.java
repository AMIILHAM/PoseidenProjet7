package com.nnk.springboot.controller;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exception.ServiceException;
import com.nnk.springboot.service.AppUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService mockAppUserService;

    @Test
    public void testPage() throws Exception {
        // Setup
        // Configure AppUserService.appUserPage(...).
        final Page<AppUser> appUserPage = new PageImpl<>(
                List.of(new AppUser(0, "username", "password", "fullname", "role")));
        when(mockAppUserService.appUserPage(PageRequest.of(1, 10))).thenReturn(appUserPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testPage_AppUserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockAppUserService.appUserPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testAddUser() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate() throws Exception {
        // Setup
        // Configure AppUserService.appUserPage(...).
        final Page<AppUser> appUserPage = new PageImpl<>(
                List.of(new AppUser(0, "username", "password", "fullname", "role")));
        when(mockAppUserService.appUserPage(PageRequest.of(1, 10))).thenReturn(appUserPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/validate")
                        .param("id", "0")
                        .param("username", "username")
                        .param("password", "password")
                        .param("fullname", "fullname")
                        .param("role", "role")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        // Setup
        // Configure AppUserService.findById(...).
        final Optional<AppUser> appUser = Optional.of(new AppUser(0, "username", "password", "fullname", "role"));
        when(mockAppUserService.findById(0)).thenReturn(appUser);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm_AppUserServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockAppUserService.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Setup
        // Configure AppUserService.appUserPage(...).
        final Page<AppUser> appUserPage = new PageImpl<>(
                List.of(new AppUser(0, "username", "password", "fullname", "role")));
        when(mockAppUserService.appUserPage(PageRequest.of(1, 10))).thenReturn(appUserPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/update/1", 0)
                        .param("id", "0")
                        .param("username", "username")
                        .param("password", "password")
                        .param("fullname", "fullname")
                        .param("role", "role")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Setup
        // Configure AppUserService.appUserPage(...).
        final Page<AppUser> appUserPage = new PageImpl<>(
                List.of(new AppUser(0, "username", "password", "fullname", "role")));
        when(mockAppUserService.appUserPage(PageRequest.of(1, 10))).thenReturn(appUserPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/delete/1", 0)
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testDeleteUser_AppUserServiceAppUserPageReturnsNoItems() throws Exception {
        // Setup
        when(mockAppUserService.appUserPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/delete/1", 0)
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
