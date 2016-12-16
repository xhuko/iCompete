package com.icompete.service;

import com.icompete.dao.EventDao;
import com.icompete.dao.RegistrationDao;
import com.icompete.dao.ResultDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bohumel
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Inject
    private ResultDao resultDao;

    @Inject
    private RegistrationDao registrationDao;

    @Inject
    private EventDao eventDao;

    @Override
    public List<Result> findAll() {
        return this.resultDao.findAll();
    }

    @Override
    public Result findById(Long id) {
        return this.resultDao.findById(id);
    }

    @Override
    public void create(Result result) {
        this.resultDao.create(result);
    }

    @Override
    public void update(Result result) {
        this.resultDao.update(result);
    }

    @Override
    public void delete(Result result) {
        this.resultDao.delete(result);
    }

    @Override
    public List<Result> findResultsByPosition(Integer position) {
        List<Result> results = new ArrayList<>();
        if (position == null) {
            return results;
        }
        List<Result> allResults = this.resultDao.findAll();
        for (Result item : allResults) {
            if (item.getPosition() == position) {
                results.add(item);
            }
        }
        return results;
    }

    @Override
    public List<Result> findResultByEvent(Event event) {

        if (event == null) {
            throw new IllegalArgumentException("Event is null");
        }

        List<Registration> eventRegistrations = registrationDao.findByEvent(event);
        List<Result> eventResults = new ArrayList<>();

        eventRegistrations.forEach((registration) -> {
            Result registrationResult = resultDao.findResultByRegistration(registration);
            if (registrationResult != null) {
                eventResults.add(registrationResult);
            }
        });

        return eventResults;
    }

    @Override
    public List<Result> findResultByEvent(Long id) {
        Event event = eventDao.findById(id);
        if (event == null) {
            throw new IllegalArgumentException("Event is null");
        }

        List<Registration> eventRegistrations = registrationDao.findByEvent(event);
        List<Result> eventResults = new ArrayList<>();

        eventRegistrations.forEach((registration) -> {
            Result registrationResult = resultDao.findResultByRegistration(registration);
            if (registrationResult != null) {
                eventResults.add(registrationResult);
            }
        });

        return eventResults;
    }

}
