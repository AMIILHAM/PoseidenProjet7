package com.nnk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    /**
     * Render the ModelAndView 403
     * Add an Object attribute with an error message
     *
     * @return a model and view "403"
     */
    @GetMapping("403")
    public String accessDenied(Model model) {
        model.addAttribute("errorMessage", "Access Denied: You don't have permission to access this resource.");
        return "403";
    }
}