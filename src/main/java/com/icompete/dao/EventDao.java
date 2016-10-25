/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
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
    public List<Event> findAll();
    
    /**
     * Get event by id
     * @param id Id of event to retrieve
     * @return Event retrieved with the given id
     */
    public Event findById(Long id);
    
    /**
     * Add a new event in the database
     * @param event Event to save in the database
     */
    public void create(Event event);
    
    /**
     * Update existing event
     * @param event Event to update
     */
    public void update(Event event);
    
    /**
     * Delete existing event
     * @param event Event to delete
     */
    public void delete(Event event);
    
}
