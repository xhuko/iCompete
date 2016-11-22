package com.icompete.dto;

import java.util.Objects;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 22/11/2016
 */
public class RuleDTO {
    private Long id;

    private String text;

    private EventDTO event;

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.getText());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RuleDTO)) {
            return false;
        }
        final RuleDTO other = (RuleDTO) obj;
        if (!Objects.equals(this.getText(), other.getText())) {
            return false;
        }
        return true;
    }
}
