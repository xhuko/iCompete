package com.icompete.service;

import java.util.*;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 * Copied from example project.
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    @Inject
    private Mapper dozer;

    @Override
    public  <T> List<T> mapTo(List<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public  <T> Set<T> mapTo(Set<?> objects, Class<T> mapToClass) {
        Set<T> mappedCollection = new HashSet<T>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return dozer.map(u,mapToClass);
    }

    @Override
    public Mapper getMapper(){
        return dozer;
    }
}
