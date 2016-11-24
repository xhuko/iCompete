/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bohumel
 */
@Repository
@Transactional
public class RegistrationDaoImpl implements RegistrationDao{

    @PersistenceContext(unitName = "default")
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
}
