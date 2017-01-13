/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.User;
import java.util.List;

/**
 *
 * @author Bohumel
 */
public interface RegistrationDao {
    
    /**
     * Get all registrations
     * @return List of all registrations stored in the database
     */
    List<Registration> findAll();
    
    /**
     * Get registration by id
     * @param id Id of registration to retrieve
     * @return Registration retrieved with the given id
     */
    Registration findById(Long id);
    
    /**
     * Add a new registration in the database
     * @param registration Registration to save in the database
     */
    void create(Registration registration);
    
    /**
     * Update existing registration
     * @param registration Registration to update
     */
    void update(Registration registration);
    
    /**
     * Delete existing registration
     * @param registration Registration to delete
     */
    void delete(Registration registration);
    
    /**
     * Find all registration to an event
     * @param event Event to search
     * @return All event registrations
     */
    List<Registration> findByEvent(Event event);
    
    /**
     * Find all registration to an user
     * @param user User to search
     * @return All user registrations
     */
    List<Registration> findByUser(User user);
}
