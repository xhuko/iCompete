package com.icompete.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.RegistrationDTO;
import com.icompete.dto.UserDTO;
import com.icompete.exception.EntityNotFoundException;

import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 23/11/2016
 */
@Service
public interface RegistrationFacade {
    Long createRegistration(UserDTO user, EventDTO event) throws EntityNotFoundException;
    Long createResult(RegistrationDTO registrationDTO, Long position);
    RegistrationDTO getRegistrationById(Long id) throws EntityNotFoundException;
    RegistrationDTO getRegistrationByUserAndEvent(UserDTO user, EventDTO event) throws EntityNotFoundException;
    Collection<RegistrationDTO> getRegistrationsByUser(UserDTO user);
    Collection<RegistrationDTO> getRegistrationsByEvent(EventDTO event);
    void deleteRegistration(RegistrationDTO registration) throws EntityNotFoundException;
}
