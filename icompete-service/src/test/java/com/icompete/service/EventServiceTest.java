package com.icompete.service;

import com.icompete.dao.RegistrationDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.User;
import com.icompete.service.config.ServiceConfiguration;
import java.util.Collections;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
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
public class EventServiceTest extends AbstractTestNGSpringContextTests {

    private final Event event = new Event();

    @Mock
    private RegistrationDao registrationDao;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeMethod
    public void createOrders() {
        event.setAddress("test");
        event.setName("test event");

        Registration registration = new Registration();

        when(registrationDao.findByEvent(any())).thenReturn(Collections.singletonList(registration));
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testEmptyPlacesInEvent() {
        event.setCapacity(5);
        Assert.assertEquals(eventService.emptyPlacesInEvent(event), 4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyPlacesInEventNull() {

        eventService.emptyPlacesInEvent(null);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterUserToEventNullUser() {
        eventService.registerUserToEvent(null, new Event());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterUserToEventNullEvent() {
        eventService.registerUserToEvent(new User(), null);
    }

    @Test
    public void testRegisterUserToEventWithoutEmptyPlace() {
        event.setCapacity(1);

        Assert.assertFalse(eventService.registerUserToEvent(new User(), event));
    }

    @Test
    public void testRegisterUserToEventWithEmptyPlace() {

        event.setCapacity(2);

        doNothing().when(registrationDao).create(any());

        Assert.assertTrue(eventService.registerUserToEvent(new User(), event));
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindEventBySportNullSport(){
        eventService.findEventsBySport(null);
    }
}
