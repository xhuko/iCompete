package com.icompete.service.facade;

import com.icompete.dto.UserDTO;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.UserFacade;
import com.icompete.service.UserService;
import com.icompete.service.config.ServiceConfiguration;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Xhulio
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserService userService;

    @Inject
    @InjectMocks
    private UserFacade userFacade;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() throws EntityNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@gmail.com");
        userDTO.setFirstName("first name");
        userDTO.setLastName("last name");
        userDTO.setPassword("password");
        userDTO.setUserName("username");

        userDTO.setUserType(UserType.SPORTSMAN);
        User user = new User();
        when(userService.createUser(user, userDTO.getPassword())).thenReturn(Long.MIN_VALUE);

        //Assert.assertEquals((Object) userFacade.createUser(userDTO), Boolean.TRUE);
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
        //Assert.assertTrue(userFacade.authenticateUser(userDTO));

    }

}
