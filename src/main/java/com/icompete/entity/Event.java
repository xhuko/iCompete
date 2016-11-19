package com.icompete.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Sport sport;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Temporal(value = TemporalType.DATE)
    private Date startDate;

    @Temporal(value = TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    public String address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Rule> rules = new HashSet<>();

    public Long getId() {
        return id;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.sport);
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + this.capacity;
        hash = 61 * hash + Objects.hashCode(this.startDate);
        hash = 61 * hash + Objects.hashCode(this.endDate);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + Objects.hashCode(this.rules);
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
        if (this.capacity != other.capacity) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sport, other.sport)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.rules, other.rules)) {
            return false;
        }
        return true;
    }

}
