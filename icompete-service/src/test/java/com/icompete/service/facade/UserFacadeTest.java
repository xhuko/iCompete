package com.icompete.service.facade;

import com.icompete.dto.UserDTO;
import com.icompete.entity.Result;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.UserFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.UserService;
import com.icompete.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Xhulio
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    private UserDTO userDTO = new UserDTO();
    private User user = new User();
    
    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private UserFacadeImpl userFacade;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        String email = "email@gmail.com";
        userDTO.setEmail(email);
        user.setEmail(email);
        String firstName = "firstName";
        userDTO.setFirstName(firstName);
        user.setFirstName(firstName);
        String lastName = "lastName";
        userDTO.setLastName(lastName);
        user.setLastName(lastName);
        String password = "password";
        userDTO.setPassword(password);
        user.setPassword(email);
        String userName = "userName";
        userDTO.setUserName(email);
        user.setUserName(email);
        userDTO.setUserType(UserType.SPORTSMAN);
        user.setUserType(UserType.SPORTSMAN);

        when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);
        when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);
    }

    @Test
    public void testCreateUser() throws EntityNotFoundException{
        when(userService.createUser(user, userDTO.getPassword())).thenReturn(1L);
        when(userService.getUsersByUserName(user.getUserName())).thenReturn(null);
        when(userService.authenticateUser(any(), any())).thenReturn(Boolean.TRUE);

        Assert.assertEquals((long)userFacade.createUser(userDTO),1L);

        when(userService.createUser(user, userDTO.getPassword())).thenReturn(null);
        when(userService.getUsersByUserName(user.getUserName())).thenReturn(user);

        Assert.assertEquals(userFacade.createUser(userDTO),null);
    }

    @Test
    public void testAuthenticateUser() {
        when(userService.authenticateUser(user, any())).thenReturn(Boolean.FALSE);
        when(userService.authenticateUser(user, userDTO.getPassword())).thenReturn(Boolean.TRUE);
        Assert.assertTrue(userFacade.authenticateUser(userDTO));
        userDTO.setPassword("pass2");
        Assert.assertFalse(userFacade.authenticateUser(userDTO));
        userDTO.setPassword("password");
    }

    @Test
    public void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(user);
        when(userService.getUserById(2L)).thenReturn(null);
        Assert.assertEquals(userDTO, userFacade.getUserById(1L));
        Assert.assertNull(userFacade.getUserById(2L));
    }

    @Test
    public void testGetUserByUserName() {
        when(userService.getUsersByUserName(any())).thenReturn(null);
        when(userService.getUsersByUserName(user.getUserName())).thenReturn(user);
        Assert.assertEquals(userDTO, userFacade.getUsersByUserName(user.getUserName()));
        Assert.assertNull(userFacade.getUsersByUserName("nnn"));
    }

    @Test
    public void testGetUserByRole() {
        List<User> list = new ArrayList<>();
        list.add(user);
        when(userService.getUsersByRole(UserType.SPORTSMAN)).thenReturn(list);
        when(userService.getUsersByRole(UserType.ADMIN)).thenReturn(new ArrayList<User>());
        Assert.assertEquals(list, userFacade.getUsersByRole(UserType.SPORTSMAN));
        Assert.assertTrue(userFacade.getUsersByRole(UserType.ADMIN).size() == 0);
    }


    @Test
    public void testUpdate() throws Exception {
        userFacade.updateUser(userDTO);
        verify(userService, times(1)).updateUser(user);
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void testDelete() throws Exception {
        userFacade.deleteUser(userDTO);
        verify(userService, times(1)).deleteUser(user);
        verify(userService, times(1)).deleteUser(any());
    }
}
