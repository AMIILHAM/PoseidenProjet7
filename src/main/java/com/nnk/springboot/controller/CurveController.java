package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.utils.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller @Slf4j
@RequiredArgsConstructor
public class CurveController {
    private final CurvePointService curvePointService;

    /**
     * Render the view curvePoint/list
     * Adds attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/list", returning the associated view
     * with attribute
     */
    @RequestMapping("/curvePoint/list")
    public String page(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@AuthenticationPrincipal UserDetails userDetails)
    {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities().toArray()[0]);
        Page<CurvePoint> curvePointPage = this.curvePointService.getPage(PageRequest.of(page, size));
        model.addAttribute("curvePoints", curvePointPage);
        return "curvePoint/list";
    }

    /**
     * Render the view curvePoint/add
     * Adds attribute CurvePoint to the model, containing a new CurvePointModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/add", returning the associated view
     * with attribute
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        model.addAttribute("curvePoint",new CurvePoint());
        return "curvePoint/add";
    }

    /**
     * Save new Curve Point to the table curvePoint if BindingResult has no errors
     *
     * @param curvePointDto the CurvePointModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "curvePoint/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDto curvePointDto, BindingResult result, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        if (!result.hasErrors()) {
            this.curvePointService.save(curvePointDto);
            return this.page(model,0,10,userDetails);
        }
        return "curvePoint/add";
    }

    /**
     * Render the view curvePoint/update with the chosen id in a model attribute
     * with the associated data of the chosen id
     * Add attribute CurvePoint to the model
     *
     * @param id the int of the id chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/update", returning the associated view
     * with attribute
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = this.curvePointService.findById(id).orElseThrow(() -> new ServiceException(ErrorMessageConstants.CURVE_POINT_IS_NOT_FOUND + " " + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }


    /**
     * Update existing Curve Point to the table curvePoint if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param curvePointDto the CurvePointModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @return a string to the address "curvePoint/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@Valid CurvePointDto curvePointDto, BindingResult result) {
        if(!result.hasErrors()){
            this.curvePointService.update(curvePointDto);
            return "redirect:/curvePoint/list";
        }
        return "/curvePoint/update";
    }

    /**
     * Delete existing Curve Point from the table curvePoint
     *
     * @param id the int of the ID chosen by the user
     * @return a string to the address "curvePoint/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id) {
        this.curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
