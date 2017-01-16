package com.icompete.service;

import com.icompete.entity.User;
import com.icompete.enums.UserType;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 24/11/2016
 */
@Service
public interface UserService {
    
    /**
     * Get a list of all users
     * @return All the users
     */
    Collection<User> getAllUsers();
    
    /**
     * Create new user
     * @param user user entity
     * @param unencryptedPassword unencrypted password
     * @return id of new user or null if user with this user name already exists
     */
    Long createUser(User user, String unencryptedPassword);

    /**
     * Try to authenticate user
     * @param user user entity
     * @param unencryptedPassword unencrypted password
     * @return true if user exists and password is valid,false otherwise
     */
    boolean authenticateUser(User user, String unencryptedPassword);

    /**
     * Find user by his id
     * @param id id of user
     * @return user entity or null if user with passed id does not exist
     */
    User getUserById(Long id);

    /**
     * Find all users with specific role
     * @param role type of role to be search for
     * @return collection of users with specific role
     */
    Collection<User> getUsersByRole(UserType role);

    /**
     * Find user with specific user name
     * @param userName user's name
     * @return user with specific name or null, if no user has this name
     */
    User getUsersByUserName(String userName);

    /**
     * Update user
     * @param user user entity
     */
    void updateUser(User user);

    /**
     * Delete user
     * @param user user entity
     */
    void deleteUser(User user);
}
