package com.icompete.mvc.controllers;

import com.icompete.dto.EventDTO;
import com.icompete.dto.SportDTO;
import com.icompete.facade.EventFacade;
import com.icompete.facade.SportFacade;
import com.icompete.service.SportService;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Xhulio
 */
@Controller
@RequestMapping("/event")
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
        model.addAttribute("eventCreate",new EventDTO());
        return "event/new";
    }
    
    @ModelAttribute("sports")
    public List<SportDTO> sports(){
        return sportFacade.getAll();
    }
    
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(){
        return "event/new";
    }
    
}
