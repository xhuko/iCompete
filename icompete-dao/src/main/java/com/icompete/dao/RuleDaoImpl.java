/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Rule;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xhulio
 */
@Transactional
@Repository
public class RuleDaoImpl implements RuleDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Rule> findAll() {
        return em.createQuery("SELECT r FROM Rule r").getResultList();
    }

    @Override
    public Rule findById(Long id) {
        return em.find(Rule.class, id);
    }

    @Override
    public void create(Rule rule) {
        em.persist(rule);
    }

    @Override
    public void update(Rule rule) {
        em.merge(rule);
    }

    @Override
    public void delete(Rule rule) {
        //This is done to get reference to unattached entity
        Rule ruleToBeRemoved = em.getReference(Rule.class, rule.getId());
        em.remove(ruleToBeRemoved);
    }
}
