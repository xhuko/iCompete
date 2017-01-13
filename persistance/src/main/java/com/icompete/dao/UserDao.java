package com.icompete.dao;

import com.icompete.entity.User;
import com.icompete.enums.UserType;

import java.util.List;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 */
public interface UserDao {

    /**
     * Get all users
     * @return List of all users stored in the database
     */
    List<User> findAll();

    /**
     * Get all users with specific user type
     * @param type type of users
     * @return List of all users stored in the database with specific type
     */
    List<User> findByUserType(UserType type);

    /**
     * Get user by id
     * @param id Id of user to retrieve
     * @return User retrieved with the given id
     */
    User findById(Long id);

    /**
     * Get user by user name
     * @param userName user name to retrieve
     * @return User retrieved with the given name or null
     */
    User findByUserName(String userName);

    /**
     * Add a new user in the database
     * @param user User to save in the database
     */
    void create(User user);

    /**
     * Update existing user
     * @param user user to update
     */
    void update(User user);

    /**
     * Delete existing user
     * @param user User to delete
     */
    void delete(User user);
}
