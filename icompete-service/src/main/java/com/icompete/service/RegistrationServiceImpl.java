package com.icompete.service;

import com.icompete.dao.RegistrationDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.User;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bohumel
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Inject
    private RegistrationDao registration;
    
    @Override
    public List<Registration> findAll() {
        return this.registration.findAll();
    }

    @Override
    public Registration findById(Long id) {
        return this.registration.findById(id);
    }

    @Override
    public void create(Registration registration) {
        this.registration.create(registration);
    }

    @Override
    public void update(Registration registration) {
        this.registration.update(registration);
    }

    @Override
    public void delete(Registration registration) {
        this.registration.delete(registration);
    }

    @Override
    public List<Registration> findRegistrationsByEvent(Event event) {
        return this.registration.findByEvent(event);
    }

    @Override
    public List<Registration> findRegistrationsByUser(User user) {
        return this.registration.findByUser(user);
    }
}
