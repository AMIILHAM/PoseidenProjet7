package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositorie.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleNameServiceImpl implements RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    @Override
    public Optional<RuleName> findById(Integer ruleNameId) {
        return this.ruleNameRepository.findById(ruleNameId);
    }

    /**
     * Get a list of all rule names
     *
     * @return page of rule names containing all rule name
     */
    @Override
    public Page<RuleName> getPage(Pageable pageable) {
        return this.ruleNameRepository.findAll(pageable);
    }

    /**
     * Save a new rule name in the DB
     * @param ruleName the RuleName to save
     */
    @Override
    public RuleName save(RuleName ruleName) {
        return this.ruleNameRepository.save(ruleName);
    }

    /**
     * update an existent rule name from the DB
     * @param ruleName the RuleName to save
     */
    @Override
    public RuleName update(RuleName ruleName) {
        return this.save(ruleName);
    }

    /**
     * Delete an existent rule name from the DB
     * @param ruleNameId the rule name ID
     */
    @Override
    public void delete(Integer ruleNameId) {
        this.findById(ruleNameId).ifPresent(ruleName -> this.ruleNameRepository.deleteById(ruleName.getId()));
    }
}
