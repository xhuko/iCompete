/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Sport;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xhulio
 */
@Repository
@Transactional
public class SportDaoImpl implements SportDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Sport> findAll() {
        return em.createQuery("SELECT s FROM Sport s").getResultList();
    }

    @Override
    public Sport findById(Long id) {
        return em.find(Sport.class, id);
    }

    @Override
    public void create(Sport sport) {
        em.persist(sport);
    }

    @Override
    public void update(Sport sport) {
        em.merge(sport);
    }

    @Override
    public void delete(Sport sport) {
        //This is done to get reference to unattached entity
        Sport sportToDelete = em.getReference(Sport.class, sport.getId());
        em.remove(sportToDelete);
    }
    
}
