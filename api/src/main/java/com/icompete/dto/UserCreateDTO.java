package com.icompete.dto;

import com.icompete.enums.UserType;
import com.icompete.utils.DayEqualsUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 22/11/2016
 */
public class UserCreateDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address = "";
    private String email = "";
    private Set<SportDTO> preferredSports = new HashSet<>();
    private UserType userType = UserType.SPORTSMAN;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<SportDTO> getPreferredSports() {
        return preferredSports;
    }

    public void setPreferredSports(Set<SportDTO> preferredSports) {
        this.preferredSports = preferredSports;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserCreateDTO)) return false;

        UserCreateDTO user = (UserCreateDTO) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getBirthDate() != null ? !DayEqualsUtils.equals(getBirthDate(), user.getBirthDate()) : user.getBirthDate() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(user.getAddress()) : user.getAddress() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getPreferredSports() != null ? !getPreferredSports().equals(user.getPreferredSports()) : user.getPreferredSports() != null)
            return false;
        return getUserType() == user.getUserType();
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPreferredSports() != null ? getPreferredSports().hashCode() : 0);
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        return result;
    }
}
