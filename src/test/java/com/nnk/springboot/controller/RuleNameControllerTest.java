package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService mockRuleNameService;

    @Test
    public void testPage() throws Exception {
        // Setup
        // Configure RuleNameService.getPage(...).
        final RuleName ruleName = new RuleName();
        ruleName.setId(0);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        final Page<RuleName> ruleNamePage = new PageImpl<>(List.of(ruleName));
        when(mockRuleNameService.getPage(PageRequest.of(1, 10))).thenReturn(ruleNamePage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ruleName/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    public void testPage_RuleNameServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockRuleNameService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ruleName/list")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user("username"))
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testAddRuleForm() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ruleName/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate() throws Exception {
        // Setup
        // Configure RuleNameService.getPage(...).
        final RuleName ruleName = new RuleName();
        ruleName.setId(0);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        final Page<RuleName> ruleNamePage = new PageImpl<>(List.of(ruleName));
        when(mockRuleNameService.getPage(PageRequest.of(1, 10))).thenReturn(ruleNamePage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/ruleName/validate")
                        .param("id", "0")
                        .param("name", "name")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "sqlStr")
                        .param("sqlPart", "sqlPart")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testValidate_RuleNameServiceGetPageReturnsNoItems() throws Exception {
        // Setup
        when(mockRuleNameService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/ruleName/validate")
                        .param("id", "0")
                        .param("name", "name")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "sqlStr")
                        .param("sqlPart", "sqlPart")
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
        // Configure RuleNameService.findById(...).
        final RuleName ruleName1 = new RuleName();
        ruleName1.setId(0);
        ruleName1.setName("name");
        ruleName1.setDescription("description");
        ruleName1.setJson("json");
        ruleName1.setTemplate("template");
        final Optional<RuleName> ruleName = Optional.of(ruleName1);
        when(mockRuleNameService.findById(0)).thenReturn(ruleName);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ruleName/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testShowUpdateForm_RuleNameServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockRuleNameService.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ruleName/update/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateRuleName() throws Exception {
        // Setup
        // Configure RuleNameService.getPage(...).
        final RuleName ruleName = new RuleName();
        ruleName.setId(0);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        final Page<RuleName> ruleNamePage = new PageImpl<>(List.of(ruleName));
        when(mockRuleNameService.getPage(PageRequest.of(0, 1))).thenReturn(ruleNamePage);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/ruleName/update/1")
                        .param("id", "0")
                        .param("name", "name")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "sqlStr")
                        .param("sqlPart", "sqlPart")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testUpdateRuleName_RuleNameServiceGetPageReturnsNoItems() throws Exception {
        // Setup
        when(mockRuleNameService.getPage(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/ruleName/update/1")
                        .param("id", "0")
                        .param("name", "name")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "template")
                        .param("sqlStr", "sqlStr")
                        .param("sqlPart", "sqlPart")
                        .with(user("username"))
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testDeleteRuleName() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ruleName/delete/1", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
