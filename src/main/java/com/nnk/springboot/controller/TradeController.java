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

    @RequestMapping("/trade/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails )
    {
        Page<Trade> trades = this.tradeService.getPage(PageRequest.of(page,size));
        model.addAttribute("trades", trades);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.tradeService.save(trade);
            return page(model,0,10,userDetails);
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("trade", this.tradeService.findById(id).orElseThrow());
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@Valid Trade trade, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if(!result.hasErrors()){
            this.tradeService.save(trade);
            return page(model,0,10,userDetails);
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        this.tradeService.delete(id);
        return "redirect:/trade/list";
    }
}
