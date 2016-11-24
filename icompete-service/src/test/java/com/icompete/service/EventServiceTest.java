package com.icompete.service;

import com.icompete.dao.EventDao;
import com.icompete.dao.RegistrationDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.service.config.ServiceConfiguration;
import java.util.Collections;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Xhulio
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private EventDao eventDao;

    @Mock
    private RegistrationDao registrationDao;

    @Inject
    @InjectMocks
    private EventService eventService;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @BeforeMethod
    public void createOrders() {
        Registration registration = new Registration();
        
        when(registrationDao.findByEvent(any())).thenReturn(Collections.singletonList(registration));
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void emptyPlacesInEventTest() {
        Event event = new Event();
        event.setAddress("test");
        event.setCapacity(5);
        event.setName("test event");

        Assert.assertEquals(eventService.emptyPlacesInEvent(event), 4);
        
    }
    
    @Test
    public void emptyPlacesInEventNullTest(){
//        exception.expect(IllegalArgumentException.class);
//        eventService.emptyPlacesInEvent(null);
        
    }
}
