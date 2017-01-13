package com.icompete.service;

import com.icompete.dao.*;
import com.icompete.entity.*;

import java.util.*;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link EventService}. This class is part of the service
 * module of the application that provides the implementation of the business
 * logic (main logic of the application).
 *
 * @author Xhulio
 */
@Service
public class EventServiceImpl implements EventService {

    private final static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    @Inject
    private EventDao eventDao;
    
    @Inject
    private RuleDao ruleDao;
    
    @Inject
    private SportDao sportDao;

    @Inject
    private RegistrationDao registrationDao;

    @Inject
    private ResultDao resultDao;

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventDao.findById(id);
    }

    @Override
    public Event create(Event event) {
        if(event == null){
            throw new IllegalArgumentException("Event is null");
        }
        return  eventDao.create(event);
    }

    @Override
    public void update(Event event) {
        if(event == null){
            throw new IllegalArgumentException("Event is null");
        }
        
        eventDao.update(event);
    }

    @Override
    public void delete(Event event) {
        if(event == null){
            throw new IllegalArgumentException("Event is null");
        }
        Collection<Registration> registrations = registrationDao.findByEvent(event);
        for (Registration registration : registrations) {
            Result result = resultDao.findResultByRegistration(registration);
            if (result != null) {
                resultDao.delete(result);
            }
            registrationDao.delete(registration);
        }

        eventDao.delete(event);
    }
    
    @Override
    public List<Event> findEventsBetween(Date startDate, Date endDate) {
        return eventDao.findEventsBetween(startDate, endDate);
    }

    @Override
    public List<Event> findEventsStartBetween(Date startDate, Date endDate) {
        return eventDao.findEventsStartBetween(startDate, endDate);
    }

    @Override
    public List<Event> findEventEndBetween(Date startDate, Date endDate) {
        return eventDao.findEventEndBetween(startDate, endDate);
    }

    @Override
    public boolean registerUserToEvent(User user, Event event) {
        
        if(user == null){
            throw new IllegalArgumentException("User is null");
        }
        
        if(event == null){
            throw new IllegalArgumentException("Event is null");
        }
        
        if(!eventHasEmptyPlaces(event.getId())){
            return false;
        }
        
        Registration registration = new Registration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setCreationDate(new Date());
        
        registrationDao.create(registration);
        
        return true;
    }
    
    @Override
    public int emptyPlacesInEvent(Long eventId){
        
        Event event = eventDao.findById(eventId);
        
        if(event == null){
            throw new IllegalArgumentException("Event does not exist");
        }
        
        int usedPlaces = registrationDao.findByEvent(event).size();
        return event.getCapacity() - usedPlaces;
    }
    
    @Override
    public Boolean eventHasEmptyPlaces(Long eventId){
        Event event = eventDao.findById(eventId);
        if(event == null){
            throw new IllegalArgumentException("Event does not exist");
        }
        return emptyPlacesInEvent(eventId) > 0;
    }

    @Override
    public List<Event> findEventsBySport(Sport sport) {
        if(sport == null){
            throw new IllegalArgumentException("Sport is null");
        }
        
        return eventDao.findEventsBySport(sport);
    }

    @Override
    public List<Event> findEventsByName(String searchTerm) {
        return eventDao.findEventsByName(searchTerm);
    }
    
    public boolean isUserRegisteredToEvent(long eventId, long userId){
        return eventDao.isUserRegisteredToEvent(eventId, userId);
    }

}
