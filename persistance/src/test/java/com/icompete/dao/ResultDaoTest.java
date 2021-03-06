package com.icompete.dao;

import com.icompete.PersistenceSampleApplicationContext;
import com.icompete.dao.EventDao;
import com.icompete.dao.RegistrationDao;
import com.icompete.dao.ResultDao;
import com.icompete.dao.UserDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import java.util.List;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Bohumel
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ResultDaoTest extends AbstractTestNGSpringContextTests {
       
    @Inject
    private RegistrationDao registrationDao;
    
    @Inject
    private EventDao eventDao;
    
    @Inject
    private ResultDao resultDao;
    
    @Inject
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
        testUser.setPassword("Password");
        testUser.setUserName("BestSportsmanEUNE1");
        testUser.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser);
        
        Registration testRegistration = new Registration();
        testRegistration.setEvent(testEvent);
        testRegistration.setUser(testUser);
        registrationDao.create(testRegistration);
        
        Result testResult = new Result();
        testResult.setPosition(12L);
        resultDao.create(testResult);

        List<Result> testList = resultDao.findAll();
        Assert.assertEquals(testList.size(), 1);
        
        Result testResultClone = resultDao.findById(testResult.getId());
        Assert.assertEquals(testResultClone.getId(), testResult.getId());
        
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
        testUser.setPassword("Password");
        testUser.setUserName("BestSportsmanEUNE7");
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
        testResult.setPosition(12L);
        resultDao.create(testResult);
        
        Result testResultClone = resultDao.findById(testResult.getId());
        Assert.assertEquals(testResultClone.getId(), testResult.getId());

        resultDao.update(testResult);

        testResultClone = resultDao.findById(testResult.getId());
        Assert.assertEquals(testResultClone.getId(), testResult.getId());
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
        testUser.setPassword("Password");
        testUser.setUserName("BestSportsmanEUNE8");
        testUser.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser);
        
        Registration firstRegistration = new Registration();
        firstRegistration.setEvent(firstEvent);
        firstRegistration.setUser(testUser);
        registrationDao.create(firstRegistration);
        
        Result testResult = new Result();
        testResult.setPosition(12L);
        resultDao.create(testResult);

        Assert.assertNotNull(resultDao.findById(testResult.getId()));
        resultDao.delete(testResult);
        Assert.assertNull(resultDao.findById(testResult.getId()));
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void checkDataAccessExceptionIsThrownTest() {
        
        resultDao.delete(new Result());
        
    }
}
