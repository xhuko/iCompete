/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Rule;
import java.util.List;

/**
 *
 * @author Xhulio
 */
public interface RuleDao {
     /**
     * Get all stored rules
     * @return List of all events stored in the database
     */
    public List<Rule> findAll();
    
    /**
     * Get rule by id
     * @param id Id of event to retrieve
     * @return Rule retrieved with the given id
     */
    public Rule findById(Long id);
    
    /**
     * Add a new rule in the database
     * @param event Rule to save in the database
     */
    public void create(Rule event);
    
    /**
     * Update existing rule
     * @param event Rule to update
     */
    public void update(Rule event);
    
    /**
     * Delete existing rule
     * @param event Rule to delete
     */
    public void delete(Rule event);
}
