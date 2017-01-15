package com.icompete.service;

import com.icompete.entity.Event;
import com.icompete.entity.Sport;
import com.icompete.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;



/**
 * An interface that defines a service access to the {@link Event} entity.
 * 
 * @author Xhulio
 */
@Service
public interface EventService {
    
    List<Event> findAll();
    
    Event findById(Long id);
    
    Event create(Event event);
    
    void update(Event event);
    
    void delete(Event event);
    
    /**
     * Get all dates that have their start date equal or greater than the passed start date
     * and their end date equal or lower than the passed date
     * @param startDate Minimum date for the event to start
     * @param endDate Maximum date for the event to end
     * @return Matched events
     */
    List<Event> findEventsBetween(Date startDate, Date endDate);
    
    /**
     * Find all events that start between two dates
     * @param startDate Start date of event will be equal or greater than this date
     * @param endDate Start date of event will be equal or lower than this date
     * @return 
     */
    List<Event> findEventsStartBetween(Date startDate, Date endDate);
    
    /**
     * Find all events that end between two dates
     * @param startDate End date of event will be equal or greater than this date
     * @param endDate End date of event will be equal or lower than this date
     * @return 
     */
    List<Event> findEventEndBetween(Date startDate, Date endDate);
    
    /**
     * Register a user to and event
     * @param user User to register
     * @param event Event where to register user
     * @return True if event has empty places and user was registered or false otherwise
     */
    boolean registerUserToEvent(User user ,Event event);
    
    /**
     * Return number of empty places left in event
     * @param event Event to check
     * @return Number of empty places left
     */
    int emptyPlacesInEvent(Long eventId);
    
    /**
     * Check if event has empty places or not
     * @param event Event to check
     * @return True if there are empty places or false otherwise
     */
    Boolean eventHasEmptyPlaces(Long eventId);
    
     /**
     * Find all events of a particular sport
     * @param sport Sport for which to find events
     * @return Found events
     */
    List<Event> findEventsBySport(Sport sport);
    
    /**
     * Find all events that name contains a search term
     * @param searchTerm Term to find in event name
     * @return Found events
     */
    List<Event> findEventsByName(String searchTerm);
    
    /**
     * Find if a particular user is registered to an event
     * @param eventId Event to search
     * @param userId User to search
     * @return 
     */
    boolean isUserRegisteredToEvent(long eventId, long userId);
    
    /**
     * Remove user registration from an event
     * @param user User to remove
     * @param event Event from where to remove user
     */
    public void deregisterUserFromEvent(User user, Event event);
}
