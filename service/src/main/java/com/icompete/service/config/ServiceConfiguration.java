package com.icompete.service.config;

import com.icompete.PersistenceSampleApplicationContext;
import com.icompete.dto.*;
import com.icompete.entity.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = {"com.icompete.service","com.icompete.service.facade"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }
    
    public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        mapping(Event.class, EventDTO.class);
            mapping(Rule.class, RuleDTO.class);
            mapping(Sport.class, SportDTO.class);
            mapping(User.class, UserDTO.class);
            mapping(Registration.class, RegistrationDTO.class);
            mapping(Result.class, ResultDTO.class);
	    }
	}
}
