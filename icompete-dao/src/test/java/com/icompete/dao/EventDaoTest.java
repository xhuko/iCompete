package com.icompete.dao;

import com.icompete.dao.EventDao;
import com.icompete.entity.Event;
import com.icompete.entity.Rule;
import com.icompete.entity.Sport;
import com.icompete.enums.SportType;
import java.util.List;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.Assert;

import javax.inject.Inject;

/**
 * Class to test EventDao CRUD operations
 * @author Xhulio
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class EventDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private EventDao eventDao;
    /**
     * Tests event creation and retrieval
     */
    @Test
    public void testCreateAndFind() {
        
        Event swimmingEvent = new Event();
        swimmingEvent.setName("Just swim");
        swimmingEvent.setCapacity(25);
        swimmingEvent.setAddress("At the swimming pool");
        
        Sport sport = new Sport();
        sport.setName("sport 1");
        sport.setDescription("test description");
        sport.setType(SportType.SUMMER);
        swimmingEvent.setSport(sport);
        
        eventDao.create(swimmingEvent);

        Event runningEvent = new Event();
        runningEvent.setName("Run Forest run");
        runningEvent.setCapacity(100);
        runningEvent.setAddress("Another adress");
        eventDao.create(runningEvent);

        List<Event> events = eventDao.findAll();
        Assert.assertEquals(events.size(), 2);
        
        Event swimmingEventTest = eventDao.findById(swimmingEvent.getId());
        Assert.assertEquals(swimmingEventTest.getName(), "Just swim");
        Assert.assertEquals(swimmingEventTest.getCapacity(), 25);
        Assert.assertEquals(swimmingEventTest.getAddress(), "At the swimming pool");
        Assert.assertEquals(swimmingEventTest.getSport().getName(), "sport 1");
        Assert.assertEquals(swimmingEventTest.getSport().getDescription(), "test description");
        Assert.assertEquals(swimmingEventTest.getSport().getType(), SportType.SUMMER);
        
    }

    /**
     * Test event update
     */
    @Test
    public void testUpdate() {
        Event boxingEvent = new Event();
        boxingEvent.setName("Boxing event");
        boxingEvent.setCapacity(25);
        boxingEvent.setAddress("At the ring");
        eventDao.create(boxingEvent);

        //Updatge event with new name
        boxingEvent.setName("Boxing");
        eventDao.update(boxingEvent);

        //Retrieve updated event and check updated name
        Event updatedEvent = eventDao.findById(boxingEvent.getId());
        Assert.assertEquals(updatedEvent.getName(), "Boxing");
    }

    /**
     * Test event deletion
     */
    @Test
    public void testDelete() {
        Event dancingEvent = new Event();
        dancingEvent.setName("Dancing event");
        dancingEvent.setCapacity(25);
        dancingEvent.setAddress("At the dance floor");
        eventDao.create(dancingEvent);

        //Check event was saved correctly in the db
        Assert.assertNotNull(eventDao.findById(dancingEvent.getId()));

        //Delete event and check it no longer exists in the db
        eventDao.delete(dancingEvent);
        
        Assert.assertNull(eventDao.findById(dancingEvent.getId()));
    }
    
    /**
     * Test rule creation when creating an event with not saved rules
     */
    @Test
    public void testRuleInsertion(){
        Event archeryEvent = new Event();
        archeryEvent.setName("Archery event");
        archeryEvent.setAddress("Test adress");
        archeryEvent.setCapacity(45);
        
        Rule archeryRule = new Rule();
        archeryRule.setText("Test rule");
        archeryEvent.addRule(archeryRule);
        
        eventDao.create(archeryEvent);
        
        Event savedEvent = eventDao.findById(archeryEvent.getId());
        
        Assert.assertEquals(savedEvent.getRules().size(), 1);
        Assert.assertTrue(savedEvent.getRules().contains(archeryRule));
    }
}
