/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Bohumel
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class RegistrationDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private RegistrationDao registrationDao;
    
    @Autowired
    private EventDao eventDao;
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testCreateAndFind() {
        Event firstEvent = new Event();
        firstEvent.setName("first");
        firstEvent.setCapacity(1);
        firstEvent.setAddress("firstAddress");
        eventDao.create(firstEvent);
        
        Event secondEvent = new Event();
        secondEvent.setName("second");
        secondEvent.setCapacity(2);
        secondEvent.setAddress("secondAddress");
        eventDao.create(secondEvent);
        
        User testUser = new User();
        testUser.setAddress("Home");
        testUser.setEmail("ILikeSports@gmail.com");
        testUser.setFirstName("Jozef");
        testUser.setLastName("Mak");
        testUser.setUserName("BestSportsmanEUNE");
        testUser.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser);
        
        Registration firstRegistration = new Registration();
        firstRegistration.setEvent(firstEvent);
        firstRegistration.setUser(testUser);
        registrationDao.create(firstRegistration);
        
        Registration secondRegistration = new Registration();
        secondRegistration.setEvent(secondEvent);
        secondRegistration.setUser(testUser);
        registrationDao.create(secondRegistration);

        List<Registration> testList = registrationDao.findAll();
        Assert.assertEquals(testList.size(), 2);
        
        Registration firstRegistrationClone = registrationDao.findById(firstRegistration.getId());
        Assert.assertEquals(firstRegistrationClone.getId(), firstRegistration.getId());
        Assert.assertEquals(firstRegistrationClone.getEvent().getName(), firstRegistration.getEvent().getName());
        Assert.assertEquals(firstRegistrationClone.getEvent(), firstEvent);
        
    }
    
    @Test
    public void testUpdate() {          
        Event firstEvent = new Event();
        firstEvent.setName("first");
        firstEvent.setCapacity(1);
        firstEvent.setAddress("firstAddress");
        eventDao.create(firstEvent);
        
        Event secondEvent = new Event();
        secondEvent.setName("second");
        secondEvent.setCapacity(2);
        secondEvent.setAddress("secondAddress");
        eventDao.create(secondEvent);
        
        User testUser = new User();
        testUser.setAddress("Home");
        testUser.setEmail("ILikeSports@gmail.com");
        testUser.setFirstName("Jozef");
        testUser.setLastName("Mak");
        testUser.setUserName("BestSportsmanEUNE");
        testUser.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser);
        
        Registration testRegistration = new Registration();
        testRegistration.setEvent(firstEvent);
        testRegistration.setUser(testUser);
        registrationDao.create(testRegistration);
        
        Registration testRegistrationClone = registrationDao.findById(testRegistration.getId());
        Assert.assertEquals(testRegistrationClone.getId(), testRegistration.getId());
        Assert.assertEquals(testRegistrationClone.getEvent(), firstEvent);

        testRegistration.setEvent(secondEvent);
        registrationDao.update(testRegistration);

        testRegistrationClone = registrationDao.findById(testRegistration.getId());
        Assert.assertEquals(testRegistrationClone.getId(), testRegistration.getId());
        Assert.assertEquals(testRegistrationClone.getEvent(), secondEvent);
    }
    
    @Test
    public void testDelete() {  
        Event firstEvent = new Event();
        firstEvent.setName("first");
        firstEvent.setCapacity(1);
        firstEvent.setAddress("firstAddress");
        eventDao.create(firstEvent);
        
        User testUser = new User();
        testUser.setAddress("Home");
        testUser.setEmail("ILikeSports@gmail.com");
        testUser.setFirstName("Jozef");
        testUser.setLastName("Mak");
        testUser.setUserName("BestSportsmanEUNE");
        testUser.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser);
        
        Registration testRegistration = new Registration();
        testRegistration.setEvent(firstEvent);
        testRegistration.setUser(testUser);
        registrationDao.create(testRegistration);

        Assert.assertNotNull(registrationDao.findById(testRegistration.getId()));
        registrationDao.delete(testRegistration);
        Assert.assertNull(registrationDao.findById(testRegistration.getId()));
    }
}
