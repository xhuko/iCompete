package com.icompete.service;

import com.icompete.dao.SportDao;
import com.icompete.entity.Sport;
import com.icompete.enums.SportType;
import com.icompete.service.config.ServiceConfiguration;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Bohumel
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class SportServiceTest extends AbstractTestNGSpringContextTests {

    private Sport sportEntity = new Sport();

    @Mock
    private SportDao sportDao;

    @Inject
    @InjectMocks
    private SportService sportService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        sportEntity.setDescription("Description");
        sportEntity.setName("Skiing");
        sportEntity.setType(SportType.SUMMER);
    }
    
    @Test
    public void createTest(){     
        doNothing().when(sportDao).create(any(Sport.class));        
        sportService.create(sportEntity);
        verify(sportDao).create(sportEntity); 
    }
    
    @Test
    public void updateTest(){            
        doNothing().when(sportDao).update(any(Sport.class));        
        sportEntity.setDescription("Nuda");
        sportService.update(sportEntity);
        verify(sportDao).update(sportEntity);
    }
    
    @Test
    public void deleteTest(){
        doNothing().when(sportDao).delete(any(Sport.class));        
        sportService.delete(sportEntity);
        verify(sportDao).delete(sportEntity);        
}
}
