package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RuleNameController {

    private final RuleNameService ruleNameService;

    /**
     * Render the view ruleName/list
     * Adds attribute ruleName to the model, containing all rule names available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "ruleName/list", returning the associated view
     * with attribute
     */
    @RequestMapping("/ruleName/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        Page<RuleName> ruleNamePage = this.ruleNameService.getPage(PageRequest.of(page, size));
        model.addAttribute("ruleNames", ruleNamePage);
        return "ruleName/list";
    }

    /**
     * Render the view ruleName/add
     * Adds attribute ruleName to the model, containing a new RuleNameModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "ruleName/add", returning the associated view
     * with attribute
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    /**
     * Save new rule name to the table ruleName if BindingResult has no errors
     *
     * @param ruleName the RuleNameModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "ruleName/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "ruleName/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (!result.hasErrors()) {
            this.ruleNameService.save(ruleName);
            return page(model, 0, 10, userDetails);
        }
        return "ruleName/add";
    }

    /**
     * Render the view ruleName/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute ruleName to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "ruleName/update", returning the associated view
     * with attribute
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = this.ruleNameService.findById(id).orElseThrow();
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Update existing rule name to the table ruleName if BindingResult has no errors
     *
     * @param ruleName the RuleNameModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "ruleName/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@Valid RuleName ruleName, BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (!result.hasErrors()) {
            this.ruleNameService.update(ruleName);
            return page(model, 0, 10, userDetails);
        }
        return "redirect:/ruleName/list";
    }

    /**
     * Delete existing rule name from the table ruleName
     *
     * @param id the int of the ID chosen by the user
     * @return a string to the address "ruleName/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        this.ruleNameService.delete(id);
        return "redirect:/ruleName/list";
    }
}
