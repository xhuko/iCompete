package com.icompete.service;

import com.icompete.dao.SportDao;
import com.icompete.entity.Sport;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link SportService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 * 
 * @author Xhulio
 */
@Service
public class SportServiceImpl implements SportService{

    @Inject
    private SportDao sportDao;
    
    @Override
    public List<Sport> findAll() {
        return sportDao.findAll();
    }

    @Override
    public Sport findById(Long id) {
        return sportDao.findById(id);
    }

    @Override
    public void create(Sport sport) {
        sportDao.create(sport);
    }

    @Override
    public void update(Sport sport) {
        sportDao.update(sport);
    }

    @Override
    public void delete(Sport sport) {
        sportDao.delete(sport);
    }
}
