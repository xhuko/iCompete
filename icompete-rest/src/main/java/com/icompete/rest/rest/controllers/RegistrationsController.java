package com.icompete.rest.rest.controllers;

import com.icompete.dto.RegistrationDTO;
import com.icompete.dto.SportDTO;
import com.icompete.facade.RegistrationFacade;
import com.icompete.facade.SportFacade;
import com.icompete.rest.rest.ApiUris;
import com.icompete.rest.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
