package com.nnk.springboot.controller;

import com.nnk.springboot.domain.AppUser;
import com.nnk.springboot.exception.ServiceException;
import com.nnk.springboot.service.AppUserService;
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
public class UserController {

    private final AppUserService appUserService;

    /**
     * Render the view user/list
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "user/list", returning the associated view
     * with attribute
     */
    @RequestMapping("/user/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails)
    {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        Page<AppUser> appUserPage = this.appUserService.appUserPage(PageRequest.of(page, size));
        model.addAttribute("users", appUserPage);
        return "user/list";
    }
    /**
     * Render the view user/add
     * @return a string to the address "user/add", returning the associated view
     * with attribute
     */
    @GetMapping("/user/add")
    public String addUser() {
        return "user/add";
    }

    /**
     * Save new user to the table user if BindingResult has no errors and if user does not exist
     *
     * @param appUser the UserModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "user/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "user/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/user/validate")
    public String validate(@Valid AppUser appUser, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) throws ServiceException {
        if (!result.hasErrors()) {
            this.appUserService.save(appUser);
            return this.page(model,0,10,userDetails);
        }
        return "user/add";
    }

    /**
     * Render the view user/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     *
     * @param id the int of the ID chosen by the admin
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "user/update", returning the associated view
     * with attribute
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        AppUser appUser = appUserService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        appUser.setPassword("");
        model.addAttribute("appUser", appUser);
        return "user/update";
    }
    /**
     * Update existing user to the table user if BindingResult has no errors
     *
     * @param appUser the UserModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "user/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid AppUser appUser, BindingResult result, Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails) throws ServiceException {
        if (result.hasErrors()) {
            return "user/update";
        }

        this.appUserService.update(appUser);
        return this.page(model,page,size,userDetails);
    }

    /**
     * Delete existing user from the table user
     *
     * @param id the int of the ID chosen by the admin
     * @param model the Model Interface, to add attributes to it
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails) {

        this.appUserService.deleteUser(id);
        return this.page(model,page,size,userDetails);
    }
}
