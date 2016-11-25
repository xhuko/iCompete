package com.icompete.dao;

import com.icompete.PersistenceSampleApplicationContext;
import com.icompete.dao.EventDao;
import com.icompete.entity.Event;
import com.icompete.entity.Rule;
import com.icompete.entity.Sport;
import com.icompete.enums.SportType;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.Assert;

import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.dao.DataAccessException;

/**
 * Class to test EventDao CRUD operations
 * @author Xhulio
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@Transactional
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
     * Test rule creation when creating an event with no rules
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
        
        eventDao.delete(archeryEvent);
    }
    
    /**
     * Find event that start and end between two dates
     */
    @Test
    public void testFindEventsBetween(){
        
        Calendar calendar = Calendar.getInstance();
        
        Event firstEvent = new Event();
        firstEvent.setName("Archery event");
        firstEvent.setAddress("Test adress");
        firstEvent.setCapacity(45);
        calendar.set(2016, 5, 4);
        firstEvent.setStartDate(calendar.getTime());
        calendar.set(2016, 5, 21);
        firstEvent.setEndDate(calendar.getTime());
        eventDao.create(firstEvent);
        
        Event secondEvent = new Event();
        secondEvent.setName("Archery event");
        secondEvent.setAddress("Test adress");
        secondEvent.setCapacity(45);
        calendar.set(2016, 6, 4);
        secondEvent.setStartDate(calendar.getTime());
        calendar.set(2016, 6, 25);
        secondEvent.setEndDate(calendar.getTime());
        eventDao.create(secondEvent);
         
        calendar.set(2016, 0, 1);
        Date startDate = calendar.getTime();
        calendar.set(2017, 0, 1);
        Date endDate = calendar.getTime();
        
        Assert.assertEquals(eventDao.findEventsBetween(startDate, endDate).size(), 2);
        
        eventDao.delete(firstEvent);
        eventDao.delete(secondEvent);
    }
    
    /**
     * Find event that start between two dates
     */
    @Test
    public void testFindEventsStartBetween(){
        
        Calendar calendar = Calendar.getInstance();
        
        Event firstEvent = new Event();
        firstEvent.setName("Archery event");
        firstEvent.setAddress("Test adress");
        firstEvent.setCapacity(45);
        calendar.set(2016, 0, 1);
        firstEvent.setStartDate(calendar.getTime());
        eventDao.create(firstEvent);
        
        Event secondEvent = new Event();
        secondEvent.setName("Archery event");
        secondEvent.setAddress("Test adress");
        secondEvent.setCapacity(45);
        calendar.set(2016, 0, 20);
        secondEvent.setStartDate(calendar.getTime());
        eventDao.create(secondEvent);
         
        calendar.set(2016, 0, 1);
        Date startDate = calendar.getTime();
        calendar.set(2016, 1, 1);
        Date endDate = calendar.getTime();
        
        Assert.assertEquals(eventDao.findEventsStartBetween(startDate, endDate).size(), 2);
        
        eventDao.delete(firstEvent);
        eventDao.delete(secondEvent);
    }
    
    /**
     * Find event that end between two dates
     */
    @Test
    public void testFindEventsEndBetween(){
        
        Calendar calendar = Calendar.getInstance();
        
        Event firstEvent = new Event();
        firstEvent.setName("Archery event");
        firstEvent.setAddress("Test adress");
        firstEvent.setCapacity(45);
        calendar.set(2016, 1, 1);
        firstEvent.setEndDate(calendar.getTime());
        eventDao.create(firstEvent);
        
        Event secondEvent = new Event();
        secondEvent.setName("Archery event");
        secondEvent.setAddress("Test adress");
        secondEvent.setCapacity(45);
        calendar.set(2016, 1, 20);
        secondEvent.setEndDate(calendar.getTime());
        eventDao.create(secondEvent);
         
        calendar.set(2016, 1, 1);
        Date startDate = calendar.getTime();
        calendar.set(2016, 2, 1);
        Date endDate = calendar.getTime();
        
        Assert.assertEquals(eventDao.findEventEndBetween(startDate, endDate).size(), 2);
        
        eventDao.delete(firstEvent);
        eventDao.delete(secondEvent);
    }
    
    @Test
    public void testFindEventsBySport(){
        Event firstEvent = new Event();
        firstEvent.setName("Archery event");
        firstEvent.setAddress("Test adress");
        firstEvent.setCapacity(45);
        
        Sport sport = new Sport();
        sport.setName("Archery");
        sport.setType(SportType.SUMMER);
        sport.setDescription("Archery sport");
        
        firstEvent.setSport(sport);
        
        eventDao.create(firstEvent);
        
        List<Event> events = eventDao.findEventsBySport(sport);
        
        Assert.assertEquals(events.size(), 1);
        
        Assert.assertEquals(events.get(0), firstEvent);
        
        Assert.assertEquals(events.get(0).getSport(), sport);
        
        eventDao.delete(firstEvent);
    }
    
    @Test
    public void testFindEventsByName(){
        Event firstEvent = new Event();
        firstEvent.setName("Archery event");
        firstEvent.setAddress("Test adress");
        firstEvent.setCapacity(45);
        
        eventDao.create(firstEvent);
        
        List<Event> events = eventDao.findEventsByName("rcher");
        
        Assert.assertEquals(events.size(), 1);
        
        Assert.assertEquals(events.get(0), firstEvent);
        
        Assert.assertEquals(eventDao.findEventsByName("asdf").size(), 0);
        
        eventDao.delete(firstEvent);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void checkDataAccessExceptionIsThrownTest() {
        
        eventDao.delete(new Event());
        
    }
}
