package com.icompete.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Bohumel
 */
@Entity
public class Result {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Registration registration;
    
    @Column(nullable = false)
    private int position;
    
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
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
        if ((obj instanceof Result)) {
            return false;
        }
        final Result other = (Result) obj;
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
