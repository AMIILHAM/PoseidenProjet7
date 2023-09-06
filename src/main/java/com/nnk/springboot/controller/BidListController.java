package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    private final BidListService bidListService;

    /**
     * Render the view bidList/list
     * Adds attribute BidList to the model, containing all Bids available in DB
     *
     * @param model Model Interface
     * @return a string to the address "bidList/list", returning the associated view
     * with attribute
     */
    @RequestMapping("/bidList/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails)
    {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        Page<BidList> bidLists = this.bidListService.getAllBidList(PageRequest.of(page, size));
        model.addAttribute("bidLists",bidLists);
        return "bidList/list";
    }

    /**
     * Render the view bidList/add
     * Adds attribute BidList to the model, containing a new BidMidListModel
     *
     * @param model for the Model Interface
     * @return a string to the address "bidList/add", returning the associated view
     * with attribute
     */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    /**
     * Save new Bid to the table bidList if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute BidList to the model, containing all Bids available in DB
     *
     * @param bid the BidListModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "bidList/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "bidList/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.bidListService.save(bid);
            return this.page(model,0,10,userDetails);
        }
        return "bidList/add";
    }

    /**
     * Render the view bidList/update with the chosen bidListId in a model attribute
     * with the associated data of the chosen ID
     * Add attribute BidList to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "bidList/update", returning the associated view
     * with attribute
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", this.bidListService.findById(id).orElseThrow());
        return "bidList/update";
    }

    /**
     * Update existing Bid to the table bidList if BindingResult has no errors
     *
     * @param bidList the BidListModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it

     * @return a string to the address "bidList/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@Valid BidList bidList, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.bidListService.update(bidList);
            return this.page(model,0,10,userDetails);
        }
        return "bidList/update";
    }

    /**
     * Delete existing Bid from the table bidList
     *
     * @param id the int of the ID chosen by the user
     * @return a string to the address "bidList/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        this.bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
