package com.icompete.dao;

import com.icompete.entity.Result;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bohumel
 */
@Repository
@Transactional
public class ResultDaoImpl implements ResultDao {

    @PersistenceContext(unitName = "default")
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
}

