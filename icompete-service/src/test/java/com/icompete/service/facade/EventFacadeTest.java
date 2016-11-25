package com.icompete.service.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.SportDTO;
import com.icompete.dto.UserDTO;
import com.icompete.entity.Event;
import com.icompete.enums.SportType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.EventFacade;
import com.icompete.service.EventService;
import com.icompete.service.config.ServiceConfiguration;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
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
public class EventFacadeTest extends AbstractTestNGSpringContextTests {

    

    @Inject
    @InjectMocks
    private EventFacade eventFacade;

    @Mock
    private EventService eventService;
    

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void testGetAllEvents() {
                EventDTO eventDTO = new EventDTO();
        
        eventDTO.setAddress("adress");
        eventDTO.setCapacity(25);
        eventDTO.setName("Event");
        
        SportDTO sportDTO = new SportDTO();
        sportDTO.setDescription("sport");
        sportDTO.setName("Sport");
        sportDTO.setType(SportType.SUMMER);
        eventDTO.setSport(sportDTO);
        eventFacade.createEvent(eventDTO);

        Assert.assertEquals(eventFacade.getAllEvents().size(), 1);
    }

    @Test
    public void testCreateEvent() throws EntityNotFoundException {
        EventDTO eventDTO = new EventDTO();
        
        eventDTO.setAddress("adress");
        eventDTO.setCapacity(25);
        eventDTO.setName("Event");
        
        SportDTO sportDTO = new SportDTO();
        sportDTO.setDescription("sport");
        sportDTO.setName("Sport");
        sportDTO.setType(SportType.SUMMER);
        eventDTO.setSport(sportDTO);
        Long eventId = eventFacade.createEvent(eventDTO);

        Assert.assertEquals(eventFacade.getEventById(eventId), eventDTO);

        EventDTO createdEvent = eventFacade.getEventById(eventId);

        eventFacade.deleteEvent(createdEvent);
    }

    @Test
    public void testGetEventById() {
        Event event = new Event();

        when(eventService.create(any())).thenReturn(new Event());
        when(eventService.findById(any())).thenReturn(event);

        Assert.assertNull(eventFacade.getEventById(Long.MIN_VALUE));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetEventBySportNull() {
        eventFacade.getEventsBySport(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateEventByNull() {
        eventFacade.updateEvent(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterUserToEventNullUser() throws EntityNotFoundException{
        eventFacade.registerUserToEvent(null, new EventDTO());
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterUserToEventNullEvent() throws EntityNotFoundException{
        eventFacade.registerUserToEvent(new UserDTO(), null);
    }
}
