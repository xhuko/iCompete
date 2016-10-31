package com.icompete.dao;

import com.icompete.entity.User;
import com.icompete.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class to test UserDao CRUD operations
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;
    /**
     * Tests user entity creation and retrieval
     */
    @Test
    public void testCreateAndFind() {
        User user = new User();
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setAddress("Address");
        user.setEmail("Email");
        user.setUserName("username");
        user.setUserType(UserType.SPORTSMAN);
        
        userDao.create(user);
        
        //Get user and test find methods
        Assert.assertNotNull(user.getId());
        User savedUser = userDao.findById(user.getId());
        Assert.assertNotNull(savedUser);
        Assert.assertEquals(savedUser.getFirstName(), "Firstname");
        Assert.assertEquals(savedUser.getLastName(), "Lastname");
        Assert.assertEquals(savedUser.getAddress(), "Address");
        Assert.assertEquals(savedUser.getEmail(), "Email");
        Assert.assertEquals(savedUser.getUserName(), "username");
        Assert.assertEquals(savedUser.getUserType(), UserType.SPORTSMAN);
    }

    /**
     * Test user entity update
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setAddress("Address");
        user.setEmail("Email");
        user.setUserName("username");
        user.setUserType(UserType.SPORTSMAN);

        userDao.create(user);

        User userBeforeUpdate = userDao.findById(user.getId());
        Assert.assertNotNull(userBeforeUpdate);

        userBeforeUpdate.setUserName("Archery");
        userDao.update(userBeforeUpdate);

        User updatedUser = userDao.findById(userBeforeUpdate.getId());
        Assert.assertEquals(updatedUser.getUserName(), "Archery");
    }

    /**
     * Test user entity deletion
     */
    @Test
    public void testDelete() {
        User user = new User();
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setAddress("Address");
        user.setEmail("Email");
        user.setUserName("username");
        user.setUserType(UserType.SPORTSMAN);
        
        userDao.create(user);
        
        Assert.assertNotNull(userDao.findById(user.getId()));
        
        userDao.delete(user);
        
        Assert.assertNull(userDao.findById(user.getId()));
    }
}
