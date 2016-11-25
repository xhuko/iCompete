package com.icompete.dao;

import com.icompete.PersistenceSampleApplicationContext;
import com.icompete.dao.SportDao;
import com.icompete.entity.Registration;
import com.icompete.entity.Sport;
import com.icompete.enums.SportType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.dao.DataAccessException;

/**
 * Class to test SportDao CRUD operations
 * @author Xhulio
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@Transactional
public class SportDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private SportDao sportDao;
    /**
     * Tests sport entity creation and retrieval
     */
    @Test
    public void testCreateAndFind() {
        
        Sport swimming = new Sport();
        swimming.setName("Swimming");
        swimming.setType(SportType.SUMMER);
        swimming.setDescription("The sport or activity of propelling oneself through water using the limbs.");
        
        sportDao.create(swimming);
        
        //Get sport and test find methods
        Sport savedSport = sportDao.findById(swimming.getId());
        Assert.assertNotNull(savedSport);
        Assert.assertEquals(savedSport.getName(), "Swimming");
        Assert.assertEquals(savedSport.getDescription(), "The sport or activity of propelling oneself through water using the limbs.");
        Assert.assertEquals(savedSport.getType(), SportType.SUMMER);
        
    }

    /**
     * Test sport entity update
     */
    @Test
    public void testUpdate() {
       Sport running = new Sport();
       running.setName("Running");
       running.setDescription("Description");
       running.setType(SportType.SUMMER);
       
       sportDao.create(running);
       
       Sport sportBeforeUpdate = sportDao.findById(running.getId());
       Assert.assertNotNull(sportBeforeUpdate);
       
       sportBeforeUpdate.setName("Archery");
       sportDao.update(sportBeforeUpdate);
       
       Sport updatedSport = sportDao.findById(sportBeforeUpdate.getId());
       Assert.assertEquals(updatedSport.getName(), "Archery");
    }

    /**
     * Test sport entity deletion
     */
    @Test
    public void testDelete() {
        Sport sport = new Sport();
        sport.setName("Name");
        sport.setDescription("Description");
        sport.setType(SportType.SUMMER);
        
        sportDao.create(sport);
        
        Assert.assertNotNull(sportDao.findById(sport.getId()));
        
        sportDao.delete(sport);
        
        Assert.assertNull(sportDao.findById(sport.getId()));
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void checkDataAccessExceptionIsThrownTest() {
        
        sportDao.delete(new Sport());
        
    }
}
