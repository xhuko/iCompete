package com.icompete.service;

import com.icompete.entity.Rule;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */

@Service
public interface RuleService {
    /**
     * Get all stored rules
     * @return List of all events stored in the database
     */
    Collection<Rule> findAll();

    /**
     * Get rule by id
     * @param id Id of event to retrieve
     * @return Rule retrieved with the given id
     */
    Rule findById(Long id);

    /**
     * Add a new rule in the database
     * @param event Rule to save in the database
     * @return id of new created rule
     */
    Long create(Rule event);

    /**
     * Update existing rule
     * @param event Rule to update
     */
    void update(Rule event);

    /**
     * Delete existing rule
     * @param event Rule to delete
     */
    void delete(Rule event);
}
