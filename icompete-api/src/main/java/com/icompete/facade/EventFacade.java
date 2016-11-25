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

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 23/11/2016
 */
public interface EventFacade {
    Long createEvent(EventDTO event);
    EventDTO getEventById(Long id);
    Collection<EventDTO> getAllEvents();
    Collection<EventDTO> getEventsBySport(SportDTO sport);
    Collection<EventDTO> getEventsByName(String name);
    Collection<EventDTO> getEventBetweenDates(Date dateFrom, Date dateTo);
    void updateEvent(EventDTO event) throws EntityNotFoundException;
    void deleteEvent(EventDTO event) throws EntityNotFoundException;
    List<ResultDTO> getEventResults(EventDTO event) throws EntityNotFoundException;
    List<EventDTO> findEventsStartBetween(Date startDate, Date endDate);
    List<EventDTO> findEventsEndBetween(Date startDate, Date endDate);
    boolean registerUserToEvent(UserDTO userDTO, EventDTO eventDTO) throws EntityNotFoundException;
}
