package com.icompete.service;

import com.icompete.dao.EventDao;
import com.icompete.dao.RegistrationDao;
import com.icompete.dao.RuleDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Sport;
import com.icompete.entity.User;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
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

    @Inject
    private EventDao eventDao;
    
    @Inject
    private RuleDao ruleDao;
    
    @Inject
    private RegistrationDao registrationDao;

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
        
        event.getRules().forEach((rule) -> {
            ruleDao.create(rule);
        });
     
        Event createdEvent = eventDao.create(event);
        
        return  createdEvent;
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
        int emptyPlaces = event.getCapacity() - usedPlaces;
        
        return emptyPlaces;
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

}
