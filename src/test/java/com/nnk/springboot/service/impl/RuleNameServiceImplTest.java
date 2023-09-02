package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositorie.RuleNameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RuleNameServiceImplTest {

    @Mock
    private RuleNameRepository mockRuleNameRepository;

    private RuleNameServiceImpl ruleNameServiceImplUnderTest;

    @Before
    public void setUp() {
        ruleNameServiceImplUnderTest = new RuleNameServiceImpl(mockRuleNameRepository);
    }

    @Test
    public void testFindById() {
        // Setup
        // Configure RuleNameRepository.findById(...).
        final RuleName ruleName1 = new RuleName();
        ruleName1.setId(0);
        ruleName1.setName("name");
        ruleName1.setDescription("description");
        ruleName1.setJson("json");
        ruleName1.setTemplate("template");
        final Optional<RuleName> ruleName = Optional.of(ruleName1);
        when(mockRuleNameRepository.findById(0)).thenReturn(ruleName);

        // Run the test
        final Optional<RuleName> result = ruleNameServiceImplUnderTest.findById(0);

        // Verify the results
    }

    @Test
    public void testFindById_RuleNameRepositoryReturnsAbsent() {
        // Setup
        when(mockRuleNameRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<RuleName> result = ruleNameServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetPage() {
        // Setup
        // Configure RuleNameRepository.findAll(...).
        final RuleName ruleName = new RuleName();
        ruleName.setId(0);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        final Page<RuleName> ruleNamePage = new PageImpl<>(List.of(ruleName));
        when(mockRuleNameRepository.findAll(any(Pageable.class))).thenReturn(ruleNamePage);

        // Run the test
        final Page<RuleName> result = ruleNameServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetPage_RuleNameRepositoryReturnsNoItems() {
        // Setup
        when(mockRuleNameRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<RuleName> result = ruleNameServiceImplUnderTest.getPage(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testSave() {
        // Setup
        final RuleName ruleName = new RuleName();
        ruleName.setId(0);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");

        // Configure RuleNameRepository.save(...).
        final RuleName ruleName1 = new RuleName();
        ruleName1.setId(0);
        ruleName1.setName("name");
        ruleName1.setDescription("description");
        ruleName1.setJson("json");
        ruleName1.setTemplate("template");
        when(mockRuleNameRepository.save(any(RuleName.class))).thenReturn(ruleName1);

        // Run the test
        final RuleName result = ruleNameServiceImplUnderTest.save(ruleName);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final RuleName ruleName = new RuleName();
        ruleName.setId(0);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");

        // Configure RuleNameRepository.save(...).
        final RuleName ruleName1 = new RuleName();
        ruleName1.setId(0);
        ruleName1.setName("name");
        ruleName1.setDescription("description");
        ruleName1.setJson("json");
        ruleName1.setTemplate("template");
        when(mockRuleNameRepository.save(any(RuleName.class))).thenReturn(ruleName1);

        // Run the test
        final RuleName result = ruleNameServiceImplUnderTest.update(ruleName);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Configure RuleNameRepository.findById(...).
        final RuleName ruleName1 = new RuleName();
        ruleName1.setId(0);
        ruleName1.setName("name");
        ruleName1.setDescription("description");
        ruleName1.setJson("json");
        ruleName1.setTemplate("template");
        final Optional<RuleName> ruleName = Optional.of(ruleName1);
        when(mockRuleNameRepository.findById(0)).thenReturn(ruleName);

        // Run the test
        ruleNameServiceImplUnderTest.delete(0);

        // Verify the results
        verify(mockRuleNameRepository).deleteById(0);
    }

    @Test
    public void testDelete_RuleNameRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockRuleNameRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        ruleNameServiceImplUnderTest.delete(0);

        // Verify the results
    }
}
