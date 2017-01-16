package com.icompete.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Bohumel
 */
@Entity
public class Registration {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Event event;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User user;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Result result;

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.getEvent());
        hash = 19 * hash + Objects.hashCode(this.getUser());
        hash = 19 * hash + Objects.hashCode(this.getCreationDate());
        hash = 19 * hash + Objects.hashCode(this.getResult());
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
        if (!(obj instanceof Registration)) {
            return false;
        }
        final Registration other = (Registration) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.getEvent(), other.getEvent())) {
            return false;
        }
        if (!Objects.equals(this.getUser(), other.getUser())) {
            return false;
        }
        if (!Objects.equals(this.getResult(), other.getResult())) {
            return false;
        }
        if (!Objects.equals(this.getCreationDate(), other.getCreationDate())) {
            return false;
        }
        return true;
    }

}
