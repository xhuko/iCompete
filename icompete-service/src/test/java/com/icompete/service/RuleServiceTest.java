package com.icompete.service;

import com.icompete.service.config.ServiceConfiguration;
import javax.inject.Inject;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author Xhulio
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RuleServiceTest extends AbstractTestNGSpringContextTests {
    
    @Inject
    private RuleService ruleService;
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullRule(){
        ruleService.create(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullRule(){
        ruleService.update(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullRule(){
        ruleService.delete(null);
    }
    
}
