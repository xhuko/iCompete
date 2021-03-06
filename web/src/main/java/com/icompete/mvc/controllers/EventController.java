package com.icompete.mvc.controllers;

import com.icompete.dto.*;
import com.icompete.entity.Result;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.EventFacade;
import com.icompete.facade.RegistrationFacade;
import com.icompete.facade.SportFacade;
import com.icompete.facade.UserFacade;
import com.icompete.mvc.form.EventCreateDTOValidator;
import com.icompete.mvc.form.ResultForm;
import com.icompete.mvc.form.ResultsForm;
import com.icompete.mvc.helper.HelperFunctions;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Xhulio
 */
@Controller
@RequestMapping("/event")
public class EventController {

    @Inject
    private EventFacade eventFacade;

    @Inject
    private SportFacade sportFacade;

    @Inject
    private UserFacade userFacade;

    @Inject
    private RegistrationFacade registrationFacade;


    @ModelAttribute("sport")
    public List<SportDTO> sports() {
        return sportFacade.getAll();
    }

    @ModelAttribute("events")
    public Collection<EventDTO> events() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2999, 1, 1);
        return eventFacade.getEventBetweenDates(new Date(), calendar.getTime());
    }

    @RequestMapping("/show")
    public String show(Model model, HttpServletRequest request) {

        Collection<EventDTO> events = eventFacade.getAllEvents();

        Map<Long, Integer> eventEmptyPlacesMap = new HashMap<>();
        Map<Long, Boolean> userRegisteredMap = new HashMap<>();

        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        for (EventDTO eventDTO : events) {

            eventEmptyPlacesMap.put(eventDTO.getId(), eventFacade.findEmptyPlacesInEvent(eventDTO.getId()));
            if (authenticatedUser != null) {
                userRegisteredMap.put(eventDTO.getId(), eventFacade.isUserRegisteredToEvent(eventDTO.getId(), authenticatedUser.getId()));
            }
        }

        model.addAttribute("eventEmptyPlaces", eventEmptyPlacesMap);
        model.addAttribute("userRegisteredMap", userRegisteredMap);

        model.addAttribute("events", events);

        return "event/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newEvent(Model model) {
        EventDTO eventCreate = new EventDTO();
        eventCreate.setCapacity(5);
        model.addAttribute("eventCreate", eventCreate);
        model.addAttribute("nowDate", new Date());
        return "event/new";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editEvent(@RequestParam("eventId") long eventId,Model model, 
            RedirectAttributes redirectAttributes) {
        EventDTO eventEdit = eventFacade.getEventById(eventId);
        
        if(eventEdit == null){
             redirectAttributes.addFlashAttribute("alert_danger", "The event could not be found");
             return "redirect:event/show";
        }
        
        model.addAttribute("eventEdit", eventEdit);
        model.addAttribute("nowDate", new Date());
        return "event/edit";
    }

    @RequestMapping(value = "/newRegistration", method = RequestMethod.GET)
    public String newRegistration(Model model) {
        RegistrationDTO registration = new RegistrationDTO();
        model.addAttribute("registration", registration);
        return "event/register";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("eventCreate") EventDTO event, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "/event/new";
        }

        eventFacade.createEvent(event);

        return "redirect:/event/show";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("eventEdit") EventDTO event, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "/event/edit";
        }

        eventFacade.updateEvent(event);

        return "redirect:/event/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEventInfo(@PathVariable("id") long id, Model model) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            model.addAttribute("results", getResults(eventDTO));
            model.addAttribute("event", eventDTO);
            return "/event/info";
        } else {
            return "/404";
        }
    }

    @RequestMapping(value = "/{id}/live", method = RequestMethod.GET)
    public String getEventLiveResults(@PathVariable("id") long id, Model model) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            model.addAttribute("results", getResults(eventDTO));
            model.addAttribute("event", eventDTO);
            return "/event/live";
        } else {
            return "/404";
        }
    }

    @RequestMapping(value = "/{id}/results", method = RequestMethod.GET)
    public String getEventResults(@PathVariable("id") long id, Model model) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            model.addAttribute("results", getResults(eventDTO));
            model.addAttribute("event", eventDTO);
            return "/event/results";
        } else {
            return "/404";
        }
    }

    @RequestMapping(value = "/{id}/results", method = RequestMethod.POST)
    public String setEventResults(@PathVariable("id") long id, HttpServletRequest request, ResultsForm formStruct, Model model) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            for (ResultForm resultForm : formStruct.getResults()) {
                UserDTO userDTO = userFacade.getUserById(resultForm.getUserId());
                RegistrationDTO registrationDTO = registrationFacade.getRegistrationByUserAndEvent(userDTO, eventDTO);
                if (registrationDTO == null) continue;
                try {
                    Long value = Long.parseLong(resultForm.getValue());
                    registrationFacade.createResult(registrationDTO, value);
                }
                catch (NumberFormatException ex) {
                    registrationFacade.createResult(registrationDTO, null);
                }
            }
            model.addAttribute("alert_success", "Results successfully updated.");
            model.addAttribute("results", getResults(eventDTO));
            model.addAttribute("event", eventDTO);
            return "/event/results";
        } else {
            return "/404";
        }
    }

    private TreeSet<ResultWithUserDTO> getResults(EventDTO eventDTO) {
        Collection<RegistrationDTO> registrationDTOS = registrationFacade.getRegistrationsByEvent(eventDTO);
        TreeSet<ResultWithUserDTO> results = new TreeSet<>();
        for (RegistrationDTO registrationDTO : registrationDTOS) {
            ResultWithUserDTO resultWithUserDTO = new ResultWithUserDTO();
            ResultDTO resultDTO = registrationDTO.getResult();
            resultWithUserDTO.setPosition((resultDTO != null ? (long)resultDTO.getPosition() : null));
            resultWithUserDTO.setUser(registrationDTO.getUser());
            results.add(resultWithUserDTO);
        }
        return results;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("registration") RegistrationDTO registration, BindingResult bindingResult,
            Model model, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach((fe) -> {
                model.addAttribute(fe.getField() + "_error", true);
            });
            return "/event/newRegistration";
        }
        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        registration.setUser(authenticatedUser);

        try {
            eventFacade.registerUserToEvent(registration.getUser(), registration.getEvent());

        } catch (EntityNotFoundException ex) {
            return "/event/newRegistration";
        }

        return "redirect:/event/show";

    }

    @RequestMapping(value = "/registerAjax", method = RequestMethod.POST)
    public @ResponseBody
    String registerAjax(@Valid @ModelAttribute("registration") RegistrationDTO registration, BindingResult bindingResult,
            Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        registration.setUser(authenticatedUser);

        try {
            eventFacade.registerUserToEvent(registration.getUser(), registration.getEvent());

        } catch (EntityNotFoundException ex) {
            return "{\"success\":false'}";
        }

        response.setContentType("application/json");
        int emptyPlaceInEvent = eventFacade.findEmptyPlacesInEvent(registration.getEvent().getId());
        return "{\"success\":true,\"emptyPlaces\":" + emptyPlaceInEvent + "}";
    }

    @RequestMapping(value = "/deregisterAjax", method = RequestMethod.POST)
    public @ResponseBody
    String deregisterAjax(@Valid @ModelAttribute("registration") RegistrationDTO registration,
            BindingResult bindingResult, Model model, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

        registration.setUser(authenticatedUser);

        eventFacade.deregisterUserFromEvent(authenticatedUser, registration.getEvent());

        response.setContentType("application/json");
        int emptyPlaceInEvent = eventFacade.findEmptyPlacesInEvent(registration.getEvent().getId());
        return "{\"success\":true,\"emptyPlaces\":" + emptyPlaceInEvent + "}";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam("eventId") long eventId, HttpServletRequest request) {

        try {
            UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

            if (authenticatedUser.getUserType().equals(UserType.ADMIN)) {
                EventDTO eventToDelete = eventFacade.getEventById(eventId);

                if (eventToDelete != null) {
                    eventFacade.deleteEvent(eventToDelete);
                }
            }
        } catch (EntityNotFoundException ex) {
            return "{\"success\":false}";
        }

        return "{\"success\":true}";
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof EventDTO) {
            binder.addValidators(new EventCreateDTOValidator());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
