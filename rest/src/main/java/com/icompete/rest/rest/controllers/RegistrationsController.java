package com.icompete.rest.rest.controllers;

import com.icompete.dto.*;
import com.icompete.facade.RegistrationFacade;
import com.icompete.facade.SportFacade;
import com.icompete.rest.rest.ApiUris;
import com.icompete.rest.rest.exceptions.ResourceAlreadyExistingException;
import com.icompete.rest.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(ApiUris.ROOT_URI_REGISTRATIONS)
public class RegistrationsController {

    @Inject
    private RegistrationFacade registrationFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RegistrationDTO getRegistration(@PathVariable("id") long id) throws Exception {
        RegistrationDTO registrationDTO = registrationFacade.getRegistrationById(id);
        if (registrationDTO != null) {
            return registrationDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RegistrationDTO createEvent(@RequestBody RegistrationDTO registration) throws Exception {
        Long id = registrationFacade.createRegistration(registration.getUser(), registration.getEvent());
        if (id == null) {
            throw new ResourceAlreadyExistingException();
        }
        RegistrationDTO registrationDTO = registrationFacade.getRegistrationById(id);
        if (registrationDTO != null) {
            return registrationDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteEvent(@PathVariable("id") long id) throws Exception {
        RegistrationDTO registrationDTO = registrationFacade.getRegistrationById(id);
        if (registrationDTO != null) {
            registrationFacade.deleteRegistration(registrationDTO);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}/result", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void addResult(@PathVariable("id") long id, @RequestBody ResultDTO resultDTO) throws Exception {
        RegistrationDTO registrationDTO = registrationFacade.getRegistrationById(id);
        if (registrationDTO != null) {
            registrationFacade.createResult(registrationDTO, resultDTO.getPosition());
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
