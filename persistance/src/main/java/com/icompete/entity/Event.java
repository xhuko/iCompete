package com.icompete.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Xhulio
 */
@Entity
public class Event {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Sport sport;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rule> rules = new HashSet<>();
    
    private String description;

    public Long getId() {
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sportId) {
        this.sport = sportId;
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

    public Set<Rule> getRules() {
        return rules;
    }
    
    public void setRules(Set<Rule> rules){
        this.rules = rules;
    }

    /**
     * Add rule for current event
     * @param rule The rule to add
     */
    public void addRule(Rule rule) {
        if (this.rules == null) {
            this.rules = new HashSet<>();
        }

        this.rules.add(rule);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.getSport());
        hash = 61 * hash + Objects.hashCode(this.getName());
        hash = 61 * hash + this.getCapacity();
        hash = 61 * hash + Objects.hashCode(this.getStartDate());
        hash = 61 * hash + Objects.hashCode(this.getEndDate());
        hash = 61 * hash + Objects.hashCode(this.getAddress());
        hash = 61 * hash + Objects.hashCode(this.getRules());
        hash = 61 * hash + Objects.hashCode(this.getDescription());
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
        if (!(obj instanceof Event)) {
            return false;
        }
        final Event other = (Event) obj;
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
        if(!Objects.equals(this.getDescription(), other.getDescription())){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + 
                ", sport=" + sport + 
                ", name=" + name + 
                ", capacity=" + capacity + 
                ", startDate=" + startDate + 
                ", endDate=" + endDate + 
                ", address=" + address + 
                ", rules=" + rules + '}';
    }
    
    

}
