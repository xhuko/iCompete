package com.icompete.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Xhulio
 */
public class ResultDTO {
    private Long id;
    
    private RegistrationDTO registrationDTO;
    
    private int position;
    
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public RegistrationDTO getRegistration() {
        return registrationDTO;
    }

    public void setRegistration(RegistrationDTO registrationDTO) {
        this.registrationDTO = registrationDTO;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getRegistration());
        hash = 59 * hash + this.getPosition();
        hash = 59 * hash + Objects.hashCode(this.getCreationDate());
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
        
        if (!(obj instanceof ResultDTO)) {
            return false;
        }
        final ResultDTO other = (ResultDTO) obj;
        if (this.getPosition() != other.getPosition()) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.getRegistration(), other.getRegistration())) {
            return false;
        }
        if (!Objects.equals(this.getCreationDate(), other.getCreationDate())) {
            return false;
        }
        return true;
    }
}
