package com.icompete.facade;

import com.icompete.dto.EventDTO;
import com.icompete.dto.EventResultsDTO;
import com.icompete.dto.ResultDTO;
import com.icompete.dto.SportDTO;
import com.icompete.dto.UserDTO;
import com.icompete.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.ws.ServiceMode;
import org.springframework.stereotype.Service;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 23/11/2016
 */
@Service
public interface EventFacade {
    Long createEvent(EventDTO event);
    EventDTO getEventById(Long id);
    Collection<EventDTO> getAllEvents();
    Collection<EventDTO> getEventsBySport(SportDTO sport);
    Collection<EventDTO> getEventsByName(String name);
    Collection<EventDTO> getEventBetweenDates(Date dateFrom, Date dateTo);
    void updateEvent(EventDTO event);
    void deleteEvent(EventDTO event) throws EntityNotFoundException;
    List<ResultDTO> getEventResults(EventDTO event) throws EntityNotFoundException;
    List<ResultDTO> getEventResults(Long id) throws EntityNotFoundException;
    List<EventDTO> findEventsStartBetween(Date startDate, Date endDate);
    List<EventDTO> findEventsEndBetween(Date startDate, Date endDate);
    boolean registerUserToEvent(UserDTO userDTO, EventDTO eventDTO) throws EntityNotFoundException;
    int findEmptyPlacesInEvent(Long eventId);
    /**
     * Find if a particular user is registered to an event
     * @param eventId Event to search
     * @param userId User to search
     * @return true if user is registered to event
     */
    boolean isUserRegisteredToEvent(long eventId, long userId);
}
