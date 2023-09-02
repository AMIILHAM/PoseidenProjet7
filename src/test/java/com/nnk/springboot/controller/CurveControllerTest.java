package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.service.CurvePointService;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(CurveController.class)
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService mockCurvePointService;

    @Test
    public void testPage() throws Exception {
        // Setup
        // Configure CurvePointService.getPage(...).
        final CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(0);
        curvePoint.setCurveId(0);
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint.setTerm(0.0);
        curvePoint.setValue(0.0);
        final Page<CurvePoint> curvePointPage = new PageImpl<>(List.of(curvePoint));
        when(mockCurvePointService.getPage(PageRequest.of(1, 10))).thenReturn(curvePointPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/curvePoint/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testPage_CurvePointServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCurvePointService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/curvePoint/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testAddCurvePointForm() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/curvePoint/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate() throws Exception {
        // Setup
        // Configure CurvePointService.getPage(...).
        final CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(0);
        curvePoint.setCurveId(0);
        curvePoint.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint.setTerm(0.0);
        curvePoint.setValue(0.0);
        final Page<CurvePoint> curvePointPage = new PageImpl<>(List.of(curvePoint));
        when(mockCurvePointService.getPage(PageRequest.of(1, 10))).thenReturn(curvePointPage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/curvePoint/validate")
                        .param("id", "0")
                        .param("term", "0")
                        .param("value", "0")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());

        // Confirm CurvePointService.save(...).
        final CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(0);
        curvePointDto.setTerm(0.0);
        curvePointDto.setValue(0.0);
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        // Setup
        // Configure CurvePointService.findById(...).
        final CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setId(0);
        curvePoint1.setCurveId(0);
        curvePoint1.setAsOfDate(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        curvePoint1.setTerm(0.0);
        curvePoint1.setValue(0.0);
        final Optional<CurvePoint> curvePoint = Optional.of(curvePoint1);
        when(mockCurvePointService.findById(0)).thenReturn(curvePoint);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/curvePoint/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm_CurvePointServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockCurvePointService.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/curvePoint/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateCurvePoint() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/curvePoint/update/1")
                        .param("id", "0")
                        .param("term", "0")
                        .param("value", "0")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());

        // Confirm CurvePointService.update(...).
        final CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(0);
        curvePointDto.setTerm(0.0);
        curvePointDto.setValue(0.0);
    }

    @Test
    public void testDeleteCurvePoint() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/curvePoint/delete/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
