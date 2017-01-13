package com.icompete.service;

import java.util.List;
import java.util.Set;

import org.dozer.Mapper;

public interface BeanMappingService {

    <T> List<T> mapTo(List<?> objects, Class<T> mapToClass);

    <T> Set<T> mapTo(Set<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);
    Mapper getMapper();
}
