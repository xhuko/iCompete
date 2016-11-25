package com.icompete.service.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.ResultDTO;
import com.icompete.dto.SportDTO;
import com.icompete.dto.UserDTO;
import com.icompete.entity.Event;
import com.icompete.entity.Result;
import com.icompete.entity.Rule;
import com.icompete.entity.Sport;
import com.icompete.entity.User;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.EventFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.EventService;
import com.icompete.service.ResultService;
import com.icompete.service.UserService;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xhulio
 */
@Service
@Transactional
public class EventFacadeImpl implements EventFacade {

    @Inject
    private EventService eventService;
    
    @Inject
    private UserService userService;
    
    @Inject
    private ResultService resultService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createEvent(EventDTO eventDTO) {

        if (eventDTO == null) {
            throw new IllegalArgumentException("Event dto is null");
        }

        Event mappedEvent = beanMappingService.mapTo(eventDTO, Event.class);
        Sport mappedSport = beanMappingService.mapTo(eventDTO.getSport(), Sport.class);
        mappedEvent.setSport(mappedSport);
        List<Rule> mappedRules = beanMappingService.mapTo(eventDTO.getRules(), Rule.class);
        mappedEvent.setRules(new HashSet<>(mappedRules));

        Event createdEvent = eventService.create(mappedEvent);

        return createdEvent.getId();
    }

    @Override
    public EventDTO getEventById(Long l) {
        Event event = eventService.findById(l);
        return event == null ? null : beanMappingService.mapTo(eventService.findById(l), EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getAllEvents() {
        return beanMappingService.mapTo(eventService.findAll(), EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getEventsBySport(SportDTO sportDTO) {
        
        if(sportDTO == null){
            throw new IllegalArgumentException("Sport is null");
        }
        
        List<Event> foundEvents = eventService.findEventsBySport(beanMappingService.mapTo(sportDTO, Sport.class));
        return beanMappingService.mapTo(foundEvents, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getEventsByName(String searchTerm) {
        List<Event> foundEvents = eventService.findEventsByName(searchTerm);
        
        return beanMappingService.mapTo(foundEvents, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getEventBetweenDates(Date startDate, Date endDate) {
        return beanMappingService.mapTo(eventService.findEventsBetween(startDate, endDate), EventDTO.class);
    }

    @Override
    public List<EventDTO> findEventsStartBetween(Date startDate, Date endDate) {
        return beanMappingService.mapTo(eventService.findEventsStartBetween(startDate, endDate),EventDTO.class);

    }

    @Override
    public List<EventDTO> findEventsEndBetween(Date startDate, Date endDate){
        return beanMappingService.mapTo(eventService.findEventEndBetween(startDate, endDate),EventDTO.class);
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
        if (eventDTO == null) {
            throw new IllegalArgumentException("Event to update is null");
        }
        eventService.update(beanMappingService.mapTo(eventDTO, Event.class));
    }

    @Override
    public void deleteEvent(EventDTO eventDTO) throws EntityNotFoundException {
        if (eventDTO == null) {
            throw new IllegalArgumentException("Event to update is null");
        }
        Event eventToDelete = eventService.findById(eventDTO.getId());

        if (eventToDelete == null) {
            throw new EntityNotFoundException("Event");
        }

        eventService.delete(eventToDelete);
    }

    @Override
    public List<ResultDTO> getEventResults(EventDTO edto) {
        if(edto == null){
            throw new IllegalArgumentException("Event is null");
        }
        
        List<Result> eventResults = resultService.findResultByEvent(beanMappingService.mapTo(edto, Event.class));
        
        return  beanMappingService.mapTo(eventResults,ResultDTO.class);
    }

    @Override
    public boolean registerUserToEvent(UserDTO userDTO, EventDTO eventDTO) throws EntityNotFoundException{
        
        if(userDTO == null){
            throw new IllegalArgumentException("User is null");
        }
        
        if(eventDTO == null){
            throw new IllegalArgumentException("Event is null");
        }
        
        User userToRegister = userService.getUserById(userDTO.getId());
        Event eventToUse = eventService.findById(eventDTO.getId());
        
        if(userToRegister == null){
            throw new EntityNotFoundException("User");
        }
        
        if(eventToUse == null){
            throw new EntityNotFoundException("Event");
        }
        
        return eventService.registerUserToEvent(userToRegister, eventToUse);
    }

}
