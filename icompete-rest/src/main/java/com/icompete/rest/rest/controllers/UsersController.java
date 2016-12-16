package com.icompete.rest.rest.controllers;

import com.icompete.dto.SportDTO;
import com.icompete.dto.UserDTO;
import com.icompete.facade.UserFacade;
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
}
