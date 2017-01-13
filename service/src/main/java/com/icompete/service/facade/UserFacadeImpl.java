package com.icompete.service.facade;

import com.icompete.dto.UserAuthenticateDTO;
import com.icompete.dto.UserCreateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.UserFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createUser(UserCreateDTO user) {
        return userService.createUser(beanMappingService.mapTo(user, User.class), user.getPassword());
    }

    @Override
    public boolean authenticateUser(UserAuthenticateDTO user) {
        User userClass = beanMappingService.mapTo(user, User.class);
        return userService.authenticateUser(userClass, user.getPassword());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userService.getUserById(id);
        if (user == null) return null;
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO getUsersByUserName(String userName) {
        return beanMappingService.mapTo(userService.getUsersByUserName(userName), UserDTO.class);
    }

    @Override
    public Collection<UserDTO> getUsersByRole(UserType role) {
        List<User> list = new ArrayList<>(userService.getUsersByRole(role));
        return beanMappingService.mapTo(list, UserDTO.class);
    }

    @Override
    public void updateUser(UserDTO user) throws EntityNotFoundException {
        if (userService.getUserById(user.getId()) == null) throw new EntityNotFoundException("User");
        userService.updateUser(beanMappingService.mapTo(user, User.class));
    }

    @Override
    public void deleteUser(UserDTO user) throws EntityNotFoundException {
        if (userService.getUserById(user.getId()) == null) throw new EntityNotFoundException("User");
        userService.deleteUser(beanMappingService.mapTo(user, User.class));
    }
}
