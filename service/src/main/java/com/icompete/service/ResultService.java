package com.icompete.service;

import com.icompete.entity.Event;
import com.icompete.entity.Result;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bohumel
 */
@Service
public interface ResultService {
    
    /**
     * Get all results
     * @return List of all results stored in the database
     */
    List<Result> findAll();
    
    /**
     * Get result by id
     * @param id Id of result to retrieve
     * @return Result retrieved with the given id
     */
    Result findById(Long id);
    
    /**
     * Add a new result in the database
     * @param result Result to save in the database
     */
    void create(Result result);
    
    /**
     * Update existing result
     * @param result Result to update
     */
    void update(Result result);
    
    /**
     * Delete existing result
     * @param result Result to delete
     */
    void delete(Result result);
    
    /**
     * Get results by position
     * @param position Position of result to retrieve
     * @return Result retrieved with the given position
     */    
    List<Result> findResultsByPosition(Integer position);
    
    /**
     * Find all result of a particular event
     * @param event Event to find result for
     * @return Results found
     */
    List<Result> findResultByEvent(Event event);

    /**
     * Find all result of a particular event
     * @param id Event to find result for
     * @return Results found
     */
    List<Result> findResultByEvent(Long id);

    /**
     * Set result of registration
     * @param registrationId id of registration
     * @param position position
     * @return id of result
     */
    Long setResult(Long registrationId, Long position);
}
