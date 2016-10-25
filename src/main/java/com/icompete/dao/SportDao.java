/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Sport;
import java.util.List;

/**
 *
 * @author Xhulio
 */
public interface SportDao {
    /**
     * Get all stored sport entities
     * @return List of all sport entities stored in the database
     */
    public List<Sport> findAll();
    
    /**
     * Get sport by id
     * @param id Id of sport to retrieve
     * @return Sport retrieved with the given id
     */
    public Sport findById(Long id);
    
    /**
     * Add a new sport in the database
     * @param event Sport to save in the database
     */
    public void create(Sport event);
    
    /**
     * Update existing sport
     * @param event Sport to update
     */
    public void update(Sport event);
    
    /**
     * Delete existing sport
     * @param event Sport to delete
     */
    public void delete(Sport event);
}
