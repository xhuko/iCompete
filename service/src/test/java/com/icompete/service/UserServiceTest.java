package com.icompete.service;

import com.icompete.dao.SportDao;
import com.icompete.dao.UserDao;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import com.icompete.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Bohumel
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    private User userEntity = new User();

    @Mock
    private UserDao userDao;

    @Mock
    private SportDao sportDao;

    @Inject
    @InjectMocks
    private UserService userService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setupUserEntity() {
        userEntity.setAddress("Home");
        userEntity.setEmail("ILikeSports@gmail.com");
        userEntity.setFirstName("Jozef");
        userEntity.setLastName("Mak");
        userEntity.setPassword("0123456789ABCDEF");
        userEntity.setUserName("BestSportsmanEUNE7");
        userEntity.setUserType(UserType.SPORTSMAN);
    }

    @Test
    public void userAuthenticationTest() {
        Assert.assertEquals(userService.authenticateUser(userEntity, "FEDCBA9876543210"), false);
    }

    @Test
    public void getUsersByRoleTest() {
        List<User> userList = new ArrayList<>();
        when(userDao.findByUserType(UserType.SPORTSMAN)).thenReturn(userList);
        ArrayList<User> usersDto = (ArrayList<User>) userService.getUsersByRole(UserType.SPORTSMAN);
        int size = usersDto.size();
        userList.add(userEntity);
        int newSize = usersDto.size();
        assertEquals(size, newSize - 1);
    }

    @Test
    public void getUsersByUserNameTest() {
        String userName = userEntity.getUserName();
        when(userDao.findByUserName(any(String.class))).thenReturn(userEntity);
        userService.getUsersByUserName(userName);
        verify(userDao).findByUserName(userName);
    }

}
