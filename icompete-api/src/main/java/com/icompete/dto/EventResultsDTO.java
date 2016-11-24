package com.icompete.dto;

import java.util.Map;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 24/11/2016
 */
public class EventResultsDTO {
    private EventDTO event;

    private Map<Integer, UserDTO> results;

    public Map<Integer, UserDTO> getResults() {
        return results;
    }

    public void setResults(Map<Integer, UserDTO> results) {
        this.results = results;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventResultsDTO that = (EventResultsDTO) o;

        if (getEvent() != null ? !getEvent().equals(that.getEvent()) : that.getEvent() != null) return false;
        return getResults() != null ? getResults().equals(that.getResults()) : that.getResults() == null;

    }

    @Override
    public int hashCode() {
        int result = getEvent() != null ? getEvent().hashCode() : 0;
        result = 31 * result + (getResults() != null ? getResults().hashCode() : 0);
        return result;
    }
}
