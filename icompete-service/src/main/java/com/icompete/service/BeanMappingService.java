package com.icompete.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
public interface BeanMappingService {
    /**
     * Map collection of objects to specific class type
     * @param objects collection of objects
     * @param mapToClass specific class type
     * @param <T> specific class type
     * @return collection of objects with specific class type
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * Map one object to specific class type
     * @param u object
     * @param mapToClass specific class type
     * @param <T> specific class type
     * @return object with specific class type
     */
    <T> T mapTo(Object u, Class<T> mapToClass);

    /**
     * Get mapper
     * @return mapper
     */
    Mapper getMapper();
}