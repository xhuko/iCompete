/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Registration;
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
    public List<Registration> findAll();
    
    /**
     * Get registration by id
     * @param id Id of registration to retrieve
     * @return Registration retrieved with the given id
     */
    public Registration findById(Long id);
    
    /**
     * Add a new registration in the database
     * @param registration Registration to save in the database
     */
    public void create(Registration registration);
    
    /**
     * Update existing registration
     * @param registration Registration to update
     */
    public void update(Registration registration);
    
    /**
     * Delete existing registration
     * @param registration Registration to delete
     */
    public void delete(Registration registration);
    
}
