package com.icompete.service;

import com.icompete.dao.RegistrationDao;
import com.icompete.dao.ResultDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import com.icompete.entity.User;
import com.icompete.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest extends AbstractTestNGSpringContextTests {
    private Registration registration = new Registration();
    private Date day = new Date();
    private Event event = new Event();
    private User user = new User();

    @Mock
    private RegistrationDao registrationDao;

    @Inject
    @InjectMocks
    private RegistrationService registrationService;

    @BeforeMethod
    public void createOrders() {
        registration.setCreationDate(day);
        registration.setEvent(event);
        registration.setUser(user);
        event.setName("event");
        user.setUserName("username");
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Registration> list = new ArrayList<>();
        list.add(registration);
        when(registrationDao.findAll()).thenReturn(list);
        Assert.assertEquals(list, registrationDao.findAll());
    }

    @Test
    public void testFindById() {
        when(registrationDao.findById(1L)).thenReturn(registration);
        when(registrationDao.findById(2L)).thenReturn(null);
        Assert.assertEquals(registration, registrationService.findById(1L));
        Assert.assertNull(registrationService.findById(2L));
    }

    @Test
    public void testCreate() {
        registrationService.create(registration);
        verify(registrationDao, times(1)).create(registration);
        verify(registrationDao, times(1)).create(any());
    }

    @Test
    public void testUpdate() {
        registrationService.update(registration);
        verify(registrationDao, times(1)).update(registration);
        verify(registrationDao, times(1)).update(any());
    }

    @Test
    public void testDelete() {
        registrationService.delete(registration);
        verify(registrationDao, times(1)).delete(registration);
        verify(registrationDao, times(1)).delete(any());
    }

    @Test
    public void testFindRegistrationsByEvent() {
        List<Registration> list = new ArrayList<>();
        list.add(registration);
        when(registrationDao.findByEvent(event)).thenReturn(list);
        Assert.assertEquals(list, registrationService.findRegistrationsByEvent(event.getId()));
    }

    @Test
    public void testFindRegistrationsByUser() {
        List<Registration> list = new ArrayList<>();
        list.add(registration);
        when(registrationDao.findByUser(user)).thenReturn(list);
        Assert.assertEquals(new ArrayList<Result>(), registrationService.findRegistrationsByUser(new User()));
        Assert.assertEquals(list, registrationService.findRegistrationsByUser(user));
    }
}
