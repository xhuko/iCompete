package com.icompete.dto;

import com.icompete.enums.SportType;
import java.util.Objects;

/**
 *
 * @author Xhulio
 */
public class SportDTO {
    
    private Long id;
    
    private String name;
    
    private String description;
    
    private SportType type;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.getName());
        hash = 47 * hash + Objects.hashCode(this.getDescription());
        hash = 47 * hash + Objects.hashCode(this.getType());
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
        if (!(obj instanceof SportDTO)) {
            return false;
        }
        final SportDTO other = (SportDTO) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }
}
