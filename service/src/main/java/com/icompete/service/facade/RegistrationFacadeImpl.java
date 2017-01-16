package com.icompete.service.facade;

import com.icompete.dto.*;
import com.icompete.entity.Event;
import com.icompete.entity.Registration;
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

import com.icompete.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bohumel
 */
@Service
@Transactional
public class RegistrationFacadeImpl implements RegistrationFacade{
    
    @Inject
    private BeanMappingService mapper;
    
    @Inject
    private RegistrationService registrationService;

    @Inject
    private ResultService resultService;

    @Inject
    private UserService userService;

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
    public Long createResult(RegistrationDTO registrationDTO, Long position) {
        return resultService.setResult(registrationDTO.getId(), position);
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
        User userEntity = userService.getUserById(user.getId());
        List<Registration> regList = registrationService.findRegistrationsByUser(userEntity);
        for(Registration item : regList){
            resultList.add(mapper.mapTo(item, RegistrationDTO.class));
        }
        return resultList;
    }

    @Override
    public Collection<RegistrationDTO> getRegistrationsByEvent(EventDTO event) {
        if(event == null){
            return new ArrayList<>();
        }
        List<Registration> regList = registrationService.findRegistrationsByEvent(event.getId());
        return mapper.mapTo(regList, RegistrationDTO.class);
    }

    @Override
    public void deleteRegistration(RegistrationDTO registration) throws EntityNotFoundException {
        registrationService.delete(registrationService.findById(registration.getId()));
    }
    
}
