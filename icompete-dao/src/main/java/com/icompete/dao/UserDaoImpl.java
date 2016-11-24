package com.icompete.dao;

import com.icompete.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.icompete.enums.UserType;
import org.springframework.stereotype.Repository;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public List<User> findByUserType(UserType type) {
        return em.createQuery("SELECT u FROM User u WHERE u.userType = :type").setParameter("type", type).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUserName(String userName) {
        return (User)em.createQuery("SELECT u FROM User u WHERE u.userName = :name").setParameter("name", userName).getSingleResult();
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        //This is done to get reference to unattached entity
        User userToDelete = em.getReference(User.class, user.getId());
        em.remove(userToDelete);
    }
}
