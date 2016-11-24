package com.icompete.service.config;

import com.icompete.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = {"com.icompete.service"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
