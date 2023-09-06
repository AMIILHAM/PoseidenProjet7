package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    private final RatingService ratingService;

    /**
     * Render the view rating/list
     * Adds attribute rating to the model, containing all ratings available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "rating/list", returning the associated view
     * with attribute
     */
    @RequestMapping("/rating/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails)
    {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        Page<Rating> ratingPage = this.ratingService.getPage(PageRequest.of(page,size));
        model.addAttribute("ratings", ratingPage);
        return "rating/list";
    }

    /**
     * Render the view rating/add
     * Adds attribute rating to the model, containing a new Rating
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "rating/add", returning the associated view
     * with attribute
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    /**
     * Save new rating to the table rating if BindingResult has no errors
     *
     * @param rating the RatingModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "rating/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "rating/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.ratingService.save(rating);
            return this.page(model,0,10,userDetails);
        }
        return "rating/add";
    }

    /**
     * Render the view rating/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute rating to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "rating/update", returning the associated view
     * with attribute
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = this.ratingService.findById(id).orElseThrow();
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Update existing rating to the table rating if BindingResult has no errors
     *
     * @param rating the RatingModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @return a string to the address "rating/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@Valid Rating rating, BindingResult result) {
        if(!result.hasErrors()){
            this.ratingService.update(rating);
            return "redirect:/rating/list";
        }
        return "/rating/update";
    }

    /**
     * Delete existing rating from the table rating
     *
     * @param id the int of the ID chosen by the user
     * @return a string to the address "rating/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
        this.ratingService.delete(id);
        return "redirect:/rating/list";
    }
}
