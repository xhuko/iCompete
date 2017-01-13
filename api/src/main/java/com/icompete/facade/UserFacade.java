package com.icompete.facade;

import com.icompete.dto.UserAuthenticateDTO;
import com.icompete.dto.UserCreateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;

import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 23/11/2016
 */
@Service
public interface UserFacade {
    Long createUser(UserCreateDTO user);
    boolean authenticateUser(UserAuthenticateDTO user);
    UserDTO getUserById(Long id);
    UserDTO getUsersByUserName(String userName);
    Collection<UserDTO> getUsersByRole(UserType role);
    void updateUser(UserDTO user) throws EntityNotFoundException;
    void deleteUser(UserDTO user) throws EntityNotFoundException;
}
