package com.icompete.service;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bohumel
 */
@Service
public interface RegistrationService {
    
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
     * Find all registrations to an event
     * @param event Event to search
     * @return All event registrations
     */
    List<Registration> findRegistrationsByEvent(Event event);
    
    /**
     * Get registration by user
     * @param user User of registration to retrieve
     * @return Registration retrieved with the given user
     */
    List<Registration> findRegistrationsByUser(User user);
}
