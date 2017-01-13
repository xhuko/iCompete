package com.icompete.rest.rest.controllers;

import com.icompete.dto.SportDTO;
import com.icompete.dto.UserAuthenticateDTO;
import com.icompete.dto.UserCreateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.facade.UserFacade;
import com.icompete.rest.rest.ApiUris;
import com.icompete.rest.rest.exceptions.InvalidParameterException;
import com.icompete.rest.rest.exceptions.ResourceAlreadyExistingException;
import com.icompete.rest.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UsersController {

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws Exception {
        UserDTO userDTO = userFacade.getUserById(id);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO) throws Exception {
        UserDTO userDTO = userFacade.getUsersByUserName(userCreateDTO.getUserName());
        if (userDTO == null) {
            Long id = userFacade.createUser(userCreateDTO);
            return userFacade.getUserById(id);
        } else {
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void createUser(@RequestBody UserAuthenticateDTO userAuthenticateDTO) throws Exception {
        if (!userFacade.authenticateUser(userAuthenticateDTO)) {
            throw new InvalidParameterException();
        }
    }
}
