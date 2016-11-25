package com.icompete.service.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.EventResultsDTO;
import com.icompete.dto.RegistrationDTO;
import com.icompete.dto.UserDTO;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
import com.icompete.entity.Result;
import com.icompete.entity.User;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.RegistrationFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.RegistrationService;
import com.icompete.service.ResultService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bohumel
 */
@Service
public class RegistrationFacadeImpl implements RegistrationFacade{
    
    @Inject
    private BeanMappingService mapper;
    
    @Inject
    private RegistrationService registrationService;
    
    @Inject
    private ResultService resultService;

    @Override
    public Long createRegistration(UserDTO user, EventDTO event) throws EntityNotFoundException {
        if(user == null || event == null){
            return 0L;
        }
        User userEntity = mapper.mapTo(user, User.class);
        Event eventEntity = mapper.mapTo(event, Event.class);
        Registration registrationEntity = new Registration();
        registrationEntity.setUser(userEntity);
        registrationEntity.setEvent(eventEntity);
        registrationEntity.setCreationDate(new Date());
        registrationService.create(registrationEntity);
        return registrationEntity.getId();
    }

    @Override
    public RegistrationDTO getRegistrationById(Long id) throws EntityNotFoundException {
        return mapper.mapTo(registrationService.findById(id), RegistrationDTO.class);
    }

    @Override
    public RegistrationDTO getRegistrationByUserAndEvent(UserDTO user, EventDTO event) throws EntityNotFoundException {
        if(user == null || event == null){
            return null;
        }
        ArrayList<RegistrationDTO> resultList = (ArrayList<RegistrationDTO>) this.getRegistrationsByUser(user);
        for(RegistrationDTO item : resultList){
            if(item.getEvent().equals(event)){
                return item;
            }
        }
        return null;        
    }

    @Override
    public Collection<RegistrationDTO> getRegistrationsByUser(UserDTO user) {
        ArrayList<RegistrationDTO> resultList = new ArrayList<>();
        if(user == null){
            return resultList;
        }
        User userEntity = mapper.mapTo(user, User.class);
        List<Registration> regList = registrationService.findRegistrationsByUser(userEntity);
        for(Registration item : regList){
            mapper.mapTo(item, RegistrationDTO.class);
        }
        return resultList;
    }

    @Override
    public Collection<RegistrationDTO> getRegistrationsByEvent(EventDTO event) {
        ArrayList<RegistrationDTO> resultList = new ArrayList<>();
        if(event == null){
            return resultList;
        }
        Event eventEntity = mapper.mapTo(event, Event.class);
        List<Registration> regList = registrationService.findRegistrationsByEvent(eventEntity);
        for(Registration item : regList){
            mapper.mapTo(item, RegistrationDTO.class);
        }
        return resultList;
    }

    @Override
    public void deleteRegistration(RegistrationDTO registration) throws EntityNotFoundException {
        registrationService.delete(registrationService.findById(registration.getId()));
    }
    
}
