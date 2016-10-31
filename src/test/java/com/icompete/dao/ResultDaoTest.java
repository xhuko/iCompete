/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
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
    
    @Test
    public void testCreateAndFind() {
        Event testEvent = new Event();
        testEvent.setName("test");
        testEvent.setCapacity(12);
        testEvent.setAddress("testAddress");
        eventDao.create(testEvent);
        
        Registration testRegistration = new Registration();
        testRegistration.setEvent(testEvent);
        testRegistration.setUserId(Long.valueOf(1));
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
        
        Registration firstRegistration = new Registration();
        firstRegistration.setEvent(firstEvent);
        firstRegistration.setUserId(Long.valueOf(1));
        registrationDao.create(firstRegistration);
        
        Registration secondRegistration = new Registration();
        secondRegistration.setEvent(firstEvent);
        secondRegistration.setUserId(Long.valueOf(2));
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
        
        Registration firstRegistration = new Registration();
        firstRegistration.setEvent(firstEvent);
        firstRegistration.setUserId(Long.valueOf(1));
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
