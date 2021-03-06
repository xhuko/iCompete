package com.icompete.dao;

import com.icompete.PersistenceSampleApplicationContext;
import com.icompete.dao.EventDao;
import com.icompete.dao.RegistrationDao;
import com.icompete.dao.UserDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
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
public class RegistrationDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private RegistrationDao registrationDao;

    @Inject
    private EventDao eventDao;

    @Inject
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
        testUser.setPassword("Password");
        testUser.setUserName("BestSportsmanEUNE6");
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
        testUser.setPassword("Password");
        testUser.setUserName("BestSportsmanEUNE5");
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
        testUser.setPassword("Password");
        testUser.setUserName("BestSportsmanEUNE4");
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

    @Test
    void testFindByEvent() {
        Event firstEvent = new Event();
        firstEvent.setName("first");
        firstEvent.setCapacity(1);
        firstEvent.setAddress("firstAddress");
        eventDao.create(firstEvent);

        User testUser1 = new User();
        testUser1.setAddress("Home");
        testUser1.setEmail("ILikeSports@gmail.com");
        testUser1.setFirstName("Jozef");
        testUser1.setLastName("Mak");
        testUser1.setPassword("Password");
        testUser1.setUserName("BestSportsmanEUNE3");
        testUser1.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser1);

        User testUser2 = new User();
        testUser2.setAddress("Home");
        testUser2.setEmail("ILikeSports@gmail.com");
        testUser2.setFirstName("Jozef");
        testUser2.setLastName("Mak");
        testUser2.setPassword("Password");
        testUser2.setUserName("BestSportsmanEUNE2");
        testUser2.setUserType(UserType.SPORTSMAN);
        userDao.create(testUser2);

        {
            Registration testRegistration = new Registration();
            testRegistration.setEvent(firstEvent);
            testRegistration.setUser(testUser1);
            registrationDao.create(testRegistration);
        }

        {
            Registration testRegistration = new Registration();
            testRegistration.setEvent(firstEvent);
            testRegistration.setUser(testUser2);
            registrationDao.create(testRegistration);
        }
        
        Assert.assertEquals(registrationDao.findByEvent(firstEvent).size(), 2);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void checkDataAccessExceptionIsThrownTest() {
        
        registrationDao.delete(new Registration());
        
    }
}
