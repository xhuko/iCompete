package com.icompete.rest.rest.controllers;

import com.icompete.dto.SportDTO;
import com.icompete.facade.SportFacade;
import com.icompete.rest.rest.ApiUris;
import com.icompete.rest.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(ApiUris.ROOT_URI_SPORTS)
public class SportsController {

    @Inject
    private SportFacade sportFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<SportDTO> getSport() {
        return sportFacade.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SportDTO getSport(@PathVariable("id") long id) throws Exception {
        SportDTO sportDTO = sportFacade.getById(id);
        if (sportDTO != null) {
            return sportDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
