package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bohumel
 */
@Repository
public class ResultDaoImpl implements ResultDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Result> findAll() {
        return em.createQuery("SELECT t FROM Result t").getResultList();
    }

    @Override
    public Result findById(Long id) {
        return em.find(Result.class, id);
    }

    @Override
    public void create(Result result) {
        em.persist(result);
    }

    @Override
    public void update(Result result) {
        em.merge(result);
    }

    @Override
    public void delete(Result result) {
        Result resultToDelete = em.getReference(Result.class, result.getId());
        em.remove(resultToDelete);
    }

    @Override
    public Result findResultByRegistration(Registration registration) {
        TypedQuery<Result> query = em.createQuery("SELECT r FROM Result r WHERE r.registration = :registrationId",Result.class);
        
        query.setParameter("registrationId", registration);
        List<Result> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}

