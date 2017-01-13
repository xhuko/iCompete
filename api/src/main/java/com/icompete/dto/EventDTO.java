package com.icompete.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Xhulio
 */
public class EventDTO {
    
    private Long id;
    
    private SportDTO sport = new SportDTO();
    
    private String name;
    
    @Range(min = 5L, max = 75L)
    private int capacity;
    
    private String description;
    
    private Date startDate;

    private Date endDate;

    @Size(min = 5)
    public String address;

    private Set<RuleDTO> rules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SportDTO getSport() {
        return sport;
    }

    public void setSport(SportDTO sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<RuleDTO> getRules() {
        return rules;
    }

    public void addRules(RuleDTO rule) {
        if(this.rules == null){
            this.rules = new HashSet<>();
        }
        this.rules.add(rule);
    }

    public void setRules(Set<RuleDTO> rules) {
        this.rules = rules;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.getSport());
        hash = 79 * hash + Objects.hashCode(this.getName());
        hash = 79 * hash + this.getCapacity();
        hash = 79 * hash + Objects.hashCode(this.getStartDate());
        hash = 79 * hash + Objects.hashCode(this.getEndDate());
        hash = 79 * hash + Objects.hashCode(this.getAddress());
        hash = 79 * hash + Objects.hashCode(this.getRules());
        hash = 79 * hash + Objects.hashCode(this.getDescription());

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
        if (!(obj instanceof EventDTO)) {
            return false;
        }
        final EventDTO other = (EventDTO) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getAddress(), other.getAddress())) {
            return false;
        }
        if (!Objects.equals(this.getSport(), other.getSport())) {
            return false;
        }
        if (!Objects.equals(this.getStartDate(), other.getStartDate())) {
            return false;
        }
        if (!Objects.equals(this.getEndDate(), other.getEndDate())) {
            return false;
        }
        if (!Objects.equals(this.getDescription(), other.getDescription())) {
            return false;
        }
        return true;
    }
    
    
}
