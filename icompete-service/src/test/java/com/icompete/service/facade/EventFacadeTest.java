package com.icompete.service.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.SportDTO;
import com.icompete.dto.UserDTO;
import com.icompete.entity.Event;
import com.icompete.entity.Sport;
import com.icompete.entity.User;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.EventFacade;
import com.icompete.facade.UserFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.EventService;
import com.icompete.service.ResultService;
import com.icompete.service.UserService;
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
import org.testng.annotations.Test;

/**
 *
 * @author Xhulio
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EventFacadeTest extends AbstractTestNGSpringContextTests {

    private final EventDTO eventDTO = new EventDTO();

    @InjectMocks
    private EventFacadeImpl eventFacade;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @Mock
    private ResultService resultService;

    @Mock
    private BeanMappingService beanMappingService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEventById() {
        eventDTO.setAddress("adress");
        eventDTO.setCapacity(25);
        eventDTO.setName("Event");
        
        Event event = new Event();
        
        when(eventService.create(any())).thenReturn(new Event());
        when(eventService.findById(any())).thenReturn(event);
        
        Assert.assertNull(eventFacade.getEventById(Long.MIN_VALUE));
    }
}
