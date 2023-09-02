package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService mockRatingService;

    @Test
    public void testPage() throws Exception {
        // Setup
        // Configure RatingService.getPage(...).
        final Page<Rating> ratingPage = new PageImpl<>(
                List.of(new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0)));
        when(mockRatingService.getPage(PageRequest.of(1, 10))).thenReturn(ratingPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/rating/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testPage_RatingServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockRatingService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/rating/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testAddRatingForm() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/rating/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate() throws Exception {
        // Setup
        // Configure RatingService.getPage(...).
        final Page<Rating> ratingPage = new PageImpl<>(
                List.of(new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0)));
        when(mockRatingService.getPage(PageRequest.of(1, 10))).thenReturn(ratingPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/rating/validate")
                        .param("id", "0")
                        .param("moodysRating", "moodysRating")
                        .param("sandpRating", "sandpRating")
                        .param("fitchRating", "fitchRating")
                        .param("orderNumber", "0")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testValidate_RatingServiceGetPageReturnsNoItems() throws Exception {
        // Setup
        when(mockRatingService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/rating/validate")
                        .param("id", "0")
                        .param("moodysRating", "moodysRating")
                        .param("sandpRating", "sandpRating")
                        .param("fitchRating", "fitchRating")
                        .param("orderNumber", "0")
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
        // Configure RatingService.findById(...).
        final Optional<Rating> rating = Optional.of(new Rating(0, "moodysRating", "sandpRating", "fitchRating", 0));
        when(mockRatingService.findById(0)).thenReturn(rating);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/rating/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm_RatingServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockRatingService.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/rating/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateRating() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/rating/update/1")
                        .param("id", "0")
                        .param("moodysRating", "moodysRating")
                        .param("sandpRating", "sandpRating")
                        .param("fitchRating", "fitchRating")
                        .param("orderNumber", "0")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testDeleteRating() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/rating/delete/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
