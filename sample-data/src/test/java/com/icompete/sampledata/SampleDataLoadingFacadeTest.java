package com.icompete.sampledata;


import com.icompete.dao.EventDao;
import com.icompete.dao.SportDao;
import com.icompete.facade.EventFacade;
import com.icompete.service.EventService;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests data loading.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@ContextConfiguration(classes = {SampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public EventDao eventDao;

    @Autowired
    public EventFacade eventFacade;

    @Autowired
    public SampleDataLoadingFacade sampleDataLoadingFacade;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createSampleData() throws IOException {
        
        Assert.assertTrue(eventDao.findAll().size() == 2, "no products");
        Assert.assertEquals(eventFacade.getAllEvents().size(), 2);

       
    }

}