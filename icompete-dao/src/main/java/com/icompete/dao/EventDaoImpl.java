package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Sport;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xhulio
 */
@Repository
@Transactional
public class EventDaoImpl implements EventDao {

    @PersistenceContext
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
    public Event create(Event event) {
        
        em.persist(event);
        return event;
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

    @Override
    public List<Event> findEventsBetween(Date startDate, Date endDate) {
        
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.startDate >= :startDate AND e.endDate <= :endDate",
                Event.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
        
    }

    @Override
    public List<Event> findEventsStartBetween(Date startDate, Date endDate) {
        
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.startDate BETWEEN :startDate AND :endDate",
                Event.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
        
    }

    @Override
    public List<Event> findEventEndBetween(Date startDate, Date endDate) {
        
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.endDate BETWEEN :startDate AND :endDate",
                Event.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
        
    }

    @Override
    public List<Event> findEventsBySport(Sport sport) {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.sport = :sportId",
                Event.class);
        query.setParameter("sportId", sport);
        return query.getResultList();
    }

    @Override
    public List<Event> findEventsByName(String searchTerm) {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.name LIKE :searchTerm",
                Event.class);
        query.setParameter("searchTerm", "%" + searchTerm + "%");
        return query.getResultList();
    }
    
    

}
