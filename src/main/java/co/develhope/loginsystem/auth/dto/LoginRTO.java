package co.develhope.loginsystem.auth.dto;

import co.develhope.loginsystem.user.dto.UserDTO;
import co.develhope.loginsystem.user.entities.User;

public class LoginRTO {

    private UserDTO user;
    private String JWT;

    public LoginRTO() { }

    public LoginRTO(UserDTO user, String JWT) {
        this.user = user;
        this.JWT = JWT;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

}
