package com.icompete.service;

import com.icompete.dao.ResultDao;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import com.icompete.service.config.ServiceConfiguration;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest extends AbstractTestNGSpringContextTests {
    private Result result = new Result();
    private Date day = new Date();

    @Mock
    private ResultDao resultDao;

    @Inject
    @InjectMocks
    private ResultService resultService;

    @BeforeMethod
    public void createOrders() {
        Registration registration = new Registration();
        result.setCreationDate(day);
        result.setPosition(3);
        result.setRegistration(registration);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Result> list = new ArrayList<>();
        list.add(result);
        when(resultDao.findAll()).thenReturn(list);
        Assert.assertEquals(list, resultService.findAll());
    }

    @Test
    public void testFindById() {
        when(resultDao.findById(1L)).thenReturn(result);
        when(resultDao.findById(2L)).thenReturn(null);
        Assert.assertEquals(result, resultService.findById(1L));
        Assert.assertNull(resultService.findById(2L));
    }

    @Test
    public void testCreate() {
        resultService.create(result);
        verify(resultDao, times(1)).create(result);
        verify(resultDao, times(1)).create(any());
    }

    @Test
    public void testUpdate() {
        resultService.update(result);
        verify(resultDao, times(1)).update(result);
        verify(resultDao, times(1)).update(any());
    }

    @Test
    public void testDelete() {
        resultService.delete(result);
        verify(resultDao, times(1)).delete(result);
        verify(resultDao, times(1)).delete(any());
    }

    @Test
    public void testFindResultsByPosition() {
        List<Result> list = new ArrayList<>();
        list.add(result);
        when(resultDao.findAll()).thenReturn(list);
        Assert.assertEquals(new ArrayList<Result>(), resultService.findResultsByPosition(1));
        Assert.assertEquals(list, resultService.findResultsByPosition(3));
    }
}
