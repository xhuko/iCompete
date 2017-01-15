package com.icompete.service.facade;

import com.icompete.dto.*;
import com.icompete.entity.Event;
import com.icompete.entity.Result;
import com.icompete.entity.Rule;
import com.icompete.entity.Sport;
import com.icompete.entity.User;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.EventFacade;
import com.icompete.service.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Xhulio
 */
@Service
@Transactional
public class EventFacadeImpl implements EventFacade {

    private final static Logger log = LoggerFactory.getLogger(EventFacadeImpl.class);

    @Inject
    private EventService eventService;

    @Inject
    private UserService userService;

    @Inject
    private SportService sportService;

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

        Event createdEvent = eventService.create(mappedEvent);

        return createdEvent.getId();
    }

    @Override
    public EventDTO getEventById(Long l) {
        Event event = eventService.findById(l);
        EventDTO foundEventDTO = (event == null ? null : beanMappingService.mapTo(event, EventDTO.class));
        return foundEventDTO;
    }

    @Override
    public Collection<EventDTO> getAllEvents() {
        List<Event> allEvents = eventService.findAll();
        return beanMappingService.mapTo(allEvents, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getEventsBySport(SportDTO sportDTO) {

        if (sportDTO == null) {
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
        List<Event> events = eventService.findEventsBetween(startDate, endDate);
        List<EventDTO> mappedEvents = new ArrayList<>();
        for(Event event :events){
            EventDTO eventDto = beanMappingService.mapTo(event, EventDTO.class);
            eventDto.setId(event.getId());
            mappedEvents.add(eventDto);
        }
        return mappedEvents;
    }

    @Override
    public List<EventDTO> findEventsStartBetween(Date startDate, Date endDate) {
        return beanMappingService.mapTo(eventService.findEventsStartBetween(startDate, endDate), EventDTO.class);

    }

    @Override
    public List<EventDTO> findEventsEndBetween(Date startDate, Date endDate) {
        return beanMappingService.mapTo(eventService.findEventEndBetween(startDate, endDate), EventDTO.class);
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
        if (eventDTO == null) {
            throw new IllegalArgumentException("Event to update is null");
        }
        log.debug("UPDATE EVENTDTO - " + eventDTO.getId());
        Event event = eventService.findById(eventDTO.getId());
        event.setRules(new HashSet<>());
        if (event.getSport() != null && eventDTO.getSport() != null) {
            if (!event.getSport().getId().equals(eventDTO.getSport().getId())) {
                Sport sport = sportService.findById(eventDTO.getSport().getId());
                if (sport == null) {
                    throw new IllegalArgumentException("Sport with id {" + eventDTO.getSport().getId() + "} was not found.");
                }
                event.setSport(sport);
            }
        }
        beanMappingService.mapTo(eventDTO, event);
        log.debug("UPDATE EVENT - " + event.toString());
        eventService.update(event);
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
        if (edto == null) {
            throw new IllegalArgumentException("Event is null");
        }

        List<Result> eventResults = resultService.findResultByEvent(beanMappingService.mapTo(edto, Event.class));

        return beanMappingService.mapTo(eventResults, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> getEventResults(Long id) {
        List<Result> eventResults = resultService.findResultByEvent(id);
        return beanMappingService.mapTo(eventResults, ResultDTO.class);
    }

    @Override
    public boolean registerUserToEvent(UserDTO userDTO, EventDTO eventDTO) throws EntityNotFoundException {

        if (userDTO == null) {
            throw new IllegalArgumentException("User is null");
        }

        if (eventDTO == null) {
            throw new IllegalArgumentException("Event is null");
        }

        User userToRegister = userService.getUserById(userDTO.getId());
        Event eventToUse = eventService.findById(eventDTO.getId());

        if (userToRegister == null) {
            throw new EntityNotFoundException("User");
        }

        if (eventToUse == null) {
            throw new EntityNotFoundException("Event");
        }

        return eventService.registerUserToEvent(userToRegister, eventToUse);
    }

    @Override
    public void deregisterUserFromEvent(UserDTO userDTO, EventDTO eventDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("User is null");
        }

        if (eventDTO == null) {
            throw new IllegalArgumentException("Event is null");
        }
        
        User userToUnregister = userService.getUserById(userDTO.getId());
        Event eventToUse = eventService.findById(eventDTO.getId());
        
        eventService.deregisterUserFromEvent(userToUnregister, eventToUse);
    }
    
    public int findEmptyPlacesInEvent(Long eventId){
        return eventService.emptyPlacesInEvent(eventId);
    }

    @Override
    public boolean isUserRegisteredToEvent(long eventId, long userId) {
        return eventService.isUserRegisteredToEvent(eventId, userId);
    }

}
