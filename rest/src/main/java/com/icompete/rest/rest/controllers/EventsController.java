package com.icompete.rest.rest.controllers;

import com.icompete.dto.*;
import com.icompete.facade.EventFacade;
import com.icompete.facade.RegistrationFacade;
import com.icompete.facade.UserFacade;
import com.icompete.rest.rest.ApiUris;
import java.util.Collection;
import java.util.TreeSet;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.icompete.rest.rest.exceptions.ResourceAlreadyExistingException;
import com.icompete.rest.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(ApiUris.ROOT_URI_EVENTS)
public class EventsController {

    @Inject
    private EventFacade eventFacade;

    @Inject
    private UserFacade userFacade;

    @Inject
    private RegistrationFacade registrationFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<EventDTO> getEvents() {
        return eventFacade.getAllEvents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final EventDTO getEvent(@PathVariable("id") long id) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            return eventDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}/results", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<ResultWithUserDTO> getEventResults(@PathVariable("id") long id) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            Collection<RegistrationDTO> registrationDTOS = registrationFacade.getRegistrationsByEvent(eventDTO);
            TreeSet<ResultWithUserDTO> results = new TreeSet<>();
            for (RegistrationDTO registrationDTO : registrationDTOS) {
                ResultWithUserDTO resultWithUserDTO = new ResultWithUserDTO();
                ResultDTO resultDTO = registrationDTO.getResult();
                resultWithUserDTO.setPosition((resultDTO != null ? (long)resultDTO.getPosition() : null));
                resultWithUserDTO.setUser(registrationDTO.getUser());
                results.add(resultWithUserDTO);
            }
            return results;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}/registrations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<RegistrationDTO> getEventRegistrations(@PathVariable("id") long id) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            return registrationFacade.getRegistrationsByEvent(eventDTO);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final EventDTO createEvent(@RequestBody EventDTO event) throws Exception {
        Long id = eventFacade.createEvent(event);
        if (id == null) {
            throw new ResourceAlreadyExistingException();
        }
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            return eventDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updateEvent(@PathVariable("id") long id, @RequestBody EventDTO event) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            eventFacade.updateEvent(event);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteEvent(@PathVariable("id") long id) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        if (eventDTO != null) {
            eventFacade.deleteEvent(eventDTO);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}/register-user/{user-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void registerUserToEvent(@PathVariable("id") long id, @PathVariable("user-id") long userId) throws Exception {
        EventDTO eventDTO = eventFacade.getEventById(id);
        UserDTO userDTO = userFacade.getUserById(userId);
        if (eventDTO != null && userDTO != null) {
            boolean success = eventFacade.registerUserToEvent(userDTO,eventDTO);
            if (!success) {
                throw new ResourceAlreadyExistingException();
            }
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
