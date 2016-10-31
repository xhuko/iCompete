/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import java.util.List;
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
public class ResultDaoTest extends AbstractTestNGSpringContextTests {
       
    @Autowired
    private RegistrationDao registrationDao;
    
    @Autowired
    private EventDao eventDao;
    
    @Autowired
    private ResultDao resultDao;
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testCreateAndFind() {
        Event testEvent = new Event();
        testEvent.setName("test");
        testEvent.setCapacity(12);
        testEvent.setAddress("testAddress");
        eventDao.create(testEvent);
        
        User testUser = new User();
        testUser.setAddress("Home");
        testUser.setEmail("ILikeSports@gmail.com");
        testUser.setFirstName("Jozef");
        testUser.setLastName("Mak");
        testUser.setUserName("BestSportsmanEUNE");
        testUser.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser);
        
        Registration testRegistration = new Registration();
        testRegistration.setEvent(testEvent);
        testRegistration.setUser(testUser);
        registrationDao.create(testRegistration);
        
        Result testResult = new Result();
        testResult.setRegistration(testRegistration);
        testResult.setPosition(12);
        resultDao.create(testResult);

        List<Result> testList = resultDao.findAll();
        Assert.assertEquals(testList.size(), 1);
        
        Result testResultClone = resultDao.findById(testResult.getId());
        Assert.assertEquals(testResultClone.getId(), testResult.getId());
        Assert.assertEquals(testResultClone.getRegistration(), testResult.getRegistration());
        Assert.assertEquals(testResultClone.getRegistration(), testRegistration);
        
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
        
        Registration firstRegistration = new Registration();
        firstRegistration.setEvent(firstEvent);
        firstRegistration.setUser(testUser);
        registrationDao.create(firstRegistration);
        
        Registration secondRegistration = new Registration();
        secondRegistration.setEvent(firstEvent);
        secondRegistration.setUser(testUser);
        registrationDao.create(secondRegistration);
        
        Result testResult = new Result();
        testResult.setRegistration(firstRegistration);
        testResult.setPosition(12);
        resultDao.create(testResult);
        
        Result testResultClone = resultDao.findById(testResult.getId());
        Assert.assertEquals(testResultClone.getId(), testResult.getId());
        Assert.assertEquals(testResultClone.getRegistration(), firstRegistration);

        testResult.setRegistration(secondRegistration);
        resultDao.update(testResult);

        testResultClone = resultDao.findById(testResult.getId());
        Assert.assertEquals(testResultClone.getId(), testResult.getId());
        Assert.assertEquals(testResultClone.getRegistration(), secondRegistration);
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
        
        Registration firstRegistration = new Registration();
        firstRegistration.setEvent(firstEvent);
        firstRegistration.setUser(testUser);
        registrationDao.create(firstRegistration);
        
        Result testResult = new Result();
        testResult.setRegistration(firstRegistration);
        testResult.setPosition(12);
        resultDao.create(testResult);

        Assert.assertNotNull(resultDao.findById(testResult.getId()));
        resultDao.delete(testResult);
        Assert.assertNull(resultDao.findById(testResult.getId()));
    }
}
