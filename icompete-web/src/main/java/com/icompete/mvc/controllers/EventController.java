package com.icompete.mvc.controllers;

import com.icompete.dto.EventDTO;
import com.icompete.dto.SportDTO;
import com.icompete.facade.EventFacade;
import com.icompete.facade.SportFacade;
import com.icompete.mvc.form.ProductCreateDTOValidator;
import com.icompete.service.SportService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Xhulio
 */
@Controller
@RequestMapping("/event")
@SessionAttributes("eventCreate")
public class EventController {
    
    @Autowired
    EventFacade eventFacade;
    
    @Autowired
    SportFacade sportFacade;
    
    @RequestMapping("/show")
    public String show(Model model){
        
        Collection<EventDTO> events = eventFacade.getAllEvents();
        
        Map<Long, Integer> eventEmptyPlacesMap = new HashMap<>();
        
        for (EventDTO eventDTO : events) {
            
            eventEmptyPlacesMap.put(eventDTO.getId(), eventFacade.findEmptyPlacesInEvent(eventDTO.getId()));
        }
        
        model.addAttribute("eventEmptyPlaces", eventEmptyPlacesMap);
        
        model.addAttribute("events",events);
        
        return "event/show";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newEvent(Model model){
        EventDTO eventCreate = new EventDTO();
        eventCreate.setCapacity(5);
        model.addAttribute("eventCreate",eventCreate);
        model.addAttribute("nowDate",new Date());
        return "event/new";
    }
    
    @ModelAttribute("sport")
    public List<SportDTO> sports(){
        return sportFacade.getAll();
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
    
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("eventCreate") EventDTO event,BindingResult bindingResult,
            Model model){
        
        if(bindingResult.hasErrors()){
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "event/new";
        }
        
        eventFacade.createEvent(event);
        
        return "event/new";
    }
    
}
