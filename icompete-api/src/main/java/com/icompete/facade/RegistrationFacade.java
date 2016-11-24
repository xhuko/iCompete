package com.icompete.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.RegistrationDTO;
import com.icompete.dto.UserDTO;
import com.icompete.exception.EntityNotFoundException;

import java.util.Collection;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 23/11/2016
 */
public interface RegistrationFacade {
    Long createRegistration(UserDTO user, EventDTO event) throws EntityNotFoundException;
    RegistrationDTO getRegistrationById(Long id) throws EntityNotFoundException;
    RegistrationDTO getRegistrationByUserAndEvent(UserDTO user, EventDTO event) throws EntityNotFoundException;
    Collection<RegistrationDTO> getRegistrationsByUser(UserDTO user);
    Collection<RegistrationDTO> getRegistrationsByEvent(EventDTO event);
    void deleteRegistration(RegistrationDTO registration) throws EntityNotFoundException;
}
