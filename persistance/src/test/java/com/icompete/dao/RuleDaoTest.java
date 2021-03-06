package com.icompete.dao;

import com.icompete.PersistenceSampleApplicationContext;
import com.icompete.dao.RuleDao;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Rule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to test RuleDao CRUD operations
 * @author Xhulio
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RuleDaoTest extends AbstractTestNGSpringContextTests {
    @Inject
    private RuleDao ruleDao;

    @Inject
    private EventDao eventDao;
    /**
     * Tests rule entity creation and retrieval
     */
    @Test
    public void testCreateAndFind() {
        
       Rule rule = new Rule();
       rule.setText("First rule");
       
       ruleDao.create(rule);
       
       Rule savedRule = ruleDao.findById(rule.getId());
       Assert.assertNotNull(savedRule);
       Assert.assertEquals(savedRule.getText(), "First rule");
       
       /*
        * We are testing for null value because we don't know the order of execution of the test
        * so we cant check for list size because more than one entity can exists at this point
        */
       Assert.assertNotNull(ruleDao.findAll());
        
    }

    /**
     * Test rule entity update
     */
    @Test
    public void testUpdate() {
       Rule rule = new Rule();
       rule.setText("Second rule");
       
       ruleDao.create(rule);
       
       rule.setText("Second");
       
       ruleDao.update(rule);
       
       Rule updatedRule = ruleDao.findById(rule.getId());
       
       Assert.assertEquals(updatedRule.getText(), "Second");
    }

    /**
     * Test rule entity deletion
     */
    @Test
    public void testDelete() {
        Rule rule = new Rule();
        rule.setText("Just a rule");
        
        ruleDao.create(rule);
        
        Assert.assertNotNull(ruleDao.findById(rule.getId()));
        
        ruleDao.delete(rule);
        
        Assert.assertNull(ruleDao.findById(rule.getId()));
    }
    
    /**
     * Test creation of rule with a unsaved Event entity
     */
    @Test
    public void testEventInsertion(){
        Rule rule = new Rule();
        rule.setText("Forth rule");
        ruleDao.create(rule);
        
        Event event = new Event();
        event.setName("Swimming");
        event.setAddress("Adress");
        event.setCapacity(30);
        Set<Rule> ruleSet = new HashSet<>();
        ruleSet.add(rule);
        event.setRules(ruleSet);
        eventDao.create(event);

        Assert.assertEquals(event.getRules().size(), 1);
        Rule savedRule = event.getRules().iterator().next();
        Assert.assertNotNull(savedRule);
        Assert.assertNotNull(ruleDao.findById(savedRule.getId()));
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void checkDataAccessExceptionIsThrownTest() {
        
        ruleDao.delete(new Rule());
        
    }
}
