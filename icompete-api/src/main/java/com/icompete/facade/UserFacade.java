package com.icompete.facade;

import com.icompete.dto.UserDTO;
import com.icompete.dto.SportDTO;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.Date;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 23/11/2016
 */
public interface UserFacade {
    Long createUser(UserDTO user) throws EntityNotFoundException;
    boolean authenticateUser(UserDTO user);
    UserDTO getUserById(Long id) throws EntityNotFoundException;
    UserDTO getUsersByUserName(String userName);
    Collection<UserDTO> getUsersByRole(UserType role);
    void updateUser(UserDTO user) throws EntityNotFoundException;
    void deleteUser(UserDTO user) throws EntityNotFoundException;
}
