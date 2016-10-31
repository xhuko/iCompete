package com.icompete.dao;

import com.icompete.entity.Result;
import java.util.List;

/**
 *
 * @author Bohumel
 */
public interface ResultDao {
    
    /**
     * Get all results
     * @return List of all results stored in the database
     */
    public List<Result> findAll();
    
    /**
     * Get result by id
     * @param id Id of result to retrieve
     * @return Result retrieved with the given id
     */
    public Result findById(Long id);
    
    /**
     * Add a new result in the database
     * @param result Result to save in the database
     */
    public void create(Result result);
    
    /**
     * Update existing result
     * @param result Result to update
     */
    public void update(Result result);
    
    /**
     * Delete existing result
     * @param result Result to delete
     */
    public void delete(Result result);
    
}
