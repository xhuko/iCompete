package com.icompete.rest.rest.controllers;

import com.icompete.rest.rest.ApiUris;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    /**
     * The main entry point of the REST API
     * Provides access to all the resources with links to resource URIs
     * Can be even extended further so that all the actions on all the resources are available
     * and can be reused in all the controllers (possibly in full HATEOAS style)
     * 
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();

        resourcesMap.put("events_uri", ApiUris.ROOT_URI_EVENTS);
        resourcesMap.put("users_uri", ApiUris.ROOT_URI_USERS);
        resourcesMap.put("sports_uri", ApiUris.ROOT_URI_SPORTS);
        resourcesMap.put("registrations_uri", ApiUris.ROOT_URI_REGISTRATIONS);
        
        return Collections.unmodifiableMap(resourcesMap);
        
    }
}
