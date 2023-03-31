package co.develhope.loginsystem.user.dto;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDateTime jwtCreatedOn;
    private boolean isActive;

    public UserDTO() { }

    public UserDTO(Long id, String name, String surname, String email, LocalDateTime jwtCreatedOn, boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.jwtCreatedOn = jwtCreatedOn;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getJwtCreatedOn() {
        return jwtCreatedOn;
    }

    public void setJwtCreatedOn(LocalDateTime jwtCreatedOn) {
        this.jwtCreatedOn = jwtCreatedOn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
