package co.develhope.loginsystem.user.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "surname")
    private String surname;
    @Column(nullable = false, unique = true, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(name = "jwt_created_on")
    private LocalDateTime jwtCreatedOn;
    @Column(nullable = false, name = "record_status")
    private boolean isActive;
    @Column(length = 36, name = "activation_code")
    private String activationCode;
    @Column(length = 36, name = "password_reset_code")
    private String passwordResetCode;

    public User() { }

    public User(Long id, String name, String surname, String email, String password, LocalDateTime jwtCreatedOn,
                boolean isActive, String activationCode, String passwordResetCode) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.jwtCreatedOn = jwtCreatedOn;
        this.isActive = isActive;
        this.activationCode = activationCode;
        this.passwordResetCode = passwordResetCode;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

}
