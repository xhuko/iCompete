/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Sport;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xhulio
 */
public interface EventDao {
    
    /**
     * Get all events
     * @return List of all events stored in the database
     */
    List<Event> findAll();
    
    /**
     * Get event by id
     * @param id Id of event to retrieve
     * @return Event retrieved with the given id
     */
    Event findById(Long id);
    
    /**
     * Add a new event in the database
     * @param event Event to save in the database
     * @return Created event
     */
    Event create(Event event);
    
    /**
     * Update existing event
     * @param event Event to update
     */
    void update(Event event);
    
    /**
     * Delete existing event
     * @param event Event to delete
     */
    void delete(Event event);
    
    /**
     * Get all events that have their start date equal or greater than the passed start date
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
}
