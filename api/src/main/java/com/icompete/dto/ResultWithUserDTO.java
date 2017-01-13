package com.icompete.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Xhulio
 */
public class ResultWithUserDTO implements Comparable {
    private Long position;

    private UserDTO user;

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getPosition());
        hash = 59 * hash + Objects.hashCode(this.getUser());
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
        
        if (!(obj instanceof ResultWithUserDTO)) {
            return false;
        }
        final ResultWithUserDTO other = (ResultWithUserDTO) obj;
        if (!Objects.equals(this.getPosition(), other.getPosition())) {
            return false;
        }
        if (!Objects.equals(this.getUser(), other.getUser())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) return -1;
        if (!(o instanceof ResultWithUserDTO)) return -1;
        ResultWithUserDTO other = (ResultWithUserDTO)o;
        if (position == null) return -1;
        if (other.getPosition() == null) return 1;
        return position.intValue() - other.getPosition().intValue();
    }
}
