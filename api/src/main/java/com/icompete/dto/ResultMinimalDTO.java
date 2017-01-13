package com.icompete.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Xhulio
 */
public class ResultMinimalDTO {
    private Long id;
    
    private int position;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.getPosition();
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
        
        if (!(obj instanceof ResultMinimalDTO)) {
            return false;
        }
        final ResultMinimalDTO other = (ResultMinimalDTO) obj;
        if (this.getPosition() != other.getPosition()) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }
}
