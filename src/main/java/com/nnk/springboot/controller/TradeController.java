package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    private final TradeService tradeService;

    /**
     * Render the view trade/list
     * Adds attribute trade to the model, containing all trades available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "trade/list", returning the associated view
     * with attribute
     */
    @RequestMapping("/trade/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails )
    {
        Page<Trade> trades = this.tradeService.getPage(PageRequest.of(page,size));
        model.addAttribute("trades", trades);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        return "trade/list";
    }

    /**
     * Render the view trade/add
     * Adds attribute trade to the model, containing a new TradeModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "trade/add", returning the associated view
     * with attribute
     */
    @GetMapping("/trade/add")
    public String addUser(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }


    /**
     * Save new trade to the table trade if BindingResult has no errors
     *
     * @param trade the TradeModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "trade/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "trade/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.tradeService.save(trade);
            return page(model,0,10,userDetails);
        }
        return "trade/add";
    }

    /**
     * Render the view trade/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute trade to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "trade/update", returning the associated view
     * with attribute
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trade", this.tradeService.findById(id).orElseThrow());
        return "trade/update";
    }

    /**
     * Update existing trade to the table trade if BindingResult has no errors
     *
     * @param trade the TradeModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "trade/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@Valid Trade trade, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.tradeService.save(trade);
            return page(model,0,10,userDetails);
        }
        return "redirect:/trade/list";
    }

    /**
     * Delete existing trade from the table trade
     *
     * @param id the int of the ID chosen by the user
     * @return a string to the address "trade/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        this.tradeService.delete(id);
        return "redirect:/trade/list";
    }
}
