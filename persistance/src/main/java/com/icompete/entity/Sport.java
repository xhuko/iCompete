package com.icompete.entity;

import com.icompete.enums.SportType;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
/**
 *
 *
 * @author Xhulio
 */
@Entity
public class Sport {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String description;
    
    @NotNull
    @Enumerated
    private SportType type;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
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
        hash = 61 * hash + Objects.hashCode(this.getName());
        hash = 61 * hash + Objects.hashCode(this.getDescription());
        hash = 61 * hash + Objects.hashCode(this.getType());
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
        
        if (!(obj instanceof Sport)) {
            return false;
        }
        final Sport other = (Sport) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sport{" + "id=" + id + 
                ", name=" + name + 
                ", description=" + description +
                ", type=" + type + '}';
    }
    
    
}
