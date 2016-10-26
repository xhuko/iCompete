/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xhulio
 */
@Repository
@Transactional
public class EventDaoImpl implements EventDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public List<Event> findAll() {
        return em.createQuery("SELECT e FROM Event e").getResultList();
    }

    @Override
    public Event findById(Long id) {
        return em.find(Event.class, id);
    }

    @Override
    public void create(Event event) {
        em.persist(event);
    }

    @Override
    public void update(Event event) {
        em.merge(event);
    }

    @Override
    public void delete(Event event) {
        Event eventToDelete = em.getReference(Event.class, event.getId());
        em.remove(eventToDelete);
    }

}
