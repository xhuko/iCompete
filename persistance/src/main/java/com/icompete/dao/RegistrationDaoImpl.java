package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.User;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bohumel
 */
@Repository
public class RegistrationDaoImpl implements RegistrationDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Registration> findAll() {
        return em.createQuery("SELECT t FROM Registration t").getResultList();
    }

    @Override
    public Registration findById(Long id) {
        return em.find(Registration.class, id);
    }

    @Override
    public void create(Registration registration) {
        registration.setCreationDate(new Date());
        em.persist(registration);
    }

    @Override
    public void update(Registration registration) {
        em.merge(registration);
    }

    @Override
    public void delete(Registration registration) {
        Registration registrationToDelete = em.getReference(Registration.class, registration.getId());
        em.remove(registrationToDelete);
    }

    @Override
    public List<Registration> findByEvent(Event event) {
        TypedQuery<Registration> query = em.createQuery("SELECT t FROM Registration t WHERE t.event = :eventId",Registration.class);
        query.setParameter("eventId", event);
        
        return query.getResultList();
    }

    @Override
    public List<Registration> findByUser(User user) {
        TypedQuery<Registration> query = em.createQuery("SELECT t FROM Registration t WHERE t.user = :userId",Registration.class);
        query.setParameter("userId", user);
        
        return query.getResultList();
    }
}
