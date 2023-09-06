package com.nnk.springboot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	/**
	 * Render the view home
	 *
	 * @param model Model Interface
	 * @return a string to the address "home", returning the associated view
	 */
	@RequestMapping("/")
	public String home(Model model,@AuthenticationPrincipal UserDetails userDetails)
	{
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
		return "home";
	}

	/**
	 * Render the view bidList/list
	 *
	 * @param model Model Interface
	 * @return a string to the address "/bidList/list", returning the associated view
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}
