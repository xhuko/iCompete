package com.icompete.service;

import com.icompete.entity.Sport;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * An interface that defines a service access to the {@link Sport} entity.
 * 
 * @author Xhulio
 */
@Service
public interface SportService {
    
    List<Sport> findAll();
    
    Sport findById(Long id);
    
    void create(Sport sport);
    
    void update(Sport sport);
    
    void delete(Sport sport);
    
}
