package com.icompete.mvc.controllers;

import com.icompete.dto.EventDTO;
import com.icompete.dto.RegistrationDTO;
import com.icompete.dto.SportDTO;
import com.icompete.dto.UserDTO;
import com.icompete.enums.SportType;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.EventFacade;
import com.icompete.facade.SportFacade;
import com.icompete.facade.UserFacade;
import com.icompete.mvc.form.ProductCreateDTOValidator;
import com.icompete.mvc.helper.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Sekan
 */
@Controller
@RequestMapping("/sport")
public class SportController {

    @Inject
    private SportFacade sportFacade;

    @ModelAttribute("sports")
    public List<SportDTO> sports() {
        return sportFacade.getAll();
    }

    @ModelAttribute("sportTypes")
    public List<String> sportTypes() {
        List<String> sportTypes = new ArrayList<>();
        for (SportType type : SportType.values()) {
            sportTypes.add(type.toString());
        }
        return sportTypes;
    }

    @RequestMapping("/list")
    public String show(Model model, HttpServletRequest request) {
        return "sport/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSport(Model model) {
        SportDTO sport = new SportDTO();
        sport.setName("");
        sport.setDescription("");
        sport.setType(SportType.INDOOR);
        model.addAttribute("sport", sport);
        return "sport/new";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editSport(@RequestParam long id, Model model) {
        SportDTO sportDTO = sportFacade.getById(id);
        if (sportDTO == null) {
            return "redirect:/sport/list";
        }
        model.addAttribute("sport", sportDTO);
        return "sport/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("sport") SportDTO sport, BindingResult bindingResult, Model model, HttpServletRequest request) {

        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        if (!authenticatedUser.getUserType().equals(UserType.ADMIN)) {
            return "redirect:/sport/list";
        }

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("sport", sport);
            return "/sport/new";
        }

        sportFacade.create(sport);
        return "redirect:/sport/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("sport") SportDTO sport, BindingResult bindingResult, Model model, HttpServletRequest request) {

        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        if (!authenticatedUser.getUserType().equals(UserType.ADMIN)) {
            return "redirect:/sport/list";
        }

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            model.addAttribute("sport", sport);
            return "/sport/edit";
        }

        try {
            sportFacade.update(sport);
        }
        catch (EntityNotFoundException ex) {
            return "redirect:/sport/list";
        }
        return "redirect:/sport/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam long sportId,  HttpServletRequest request, Model model) {

        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        if (!authenticatedUser.getUserType().equals(UserType.ADMIN)) {
            return "redirect:/sport/list";
        }

        try {
            sportFacade.delete(sportId);
        }
        catch (EntityNotFoundException ex) {
            return "redirect:/sport/list";
        }
        catch (DataIntegrityViolationException ex) {
            model.addAttribute("alert_danger", "Cannot delete sport because of existed user or event related to this sport.");
            return "/sport/list";
        }

        model.addAttribute("alert_success", "Sport was successfully deleted.");
        return "redirect:/sport/list";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof EventDTO) {
            binder.addValidators(new ProductCreateDTOValidator());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
