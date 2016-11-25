package com.icompete.service.facade;

import com.icompete.dto.UserDTO;
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

    @Inject
    @InjectMocks
    private UserFacade userFacade;

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

        //Assert.assertEquals((long)userFacade.createUser(userDTO),1L);
    }

    @Test
    public void testAuthenticateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@gmail.com");
        userDTO.setFirstName("first name");
        userDTO.setLastName("last name");
        userDTO.setPassword("password");
        userDTO.setUserName("username");

        when(userService.authenticateUser(any(), any())).thenReturn(Boolean.TRUE);
       // Assert.assertTrue(userFacade.authenticateUser(userDTO));

    }

}
