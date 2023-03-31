package co.develhope.loginsystem.user.controllers;

import co.develhope.loginsystem.auth.dto.LoginRTO;
import co.develhope.loginsystem.auth.services.LoginService;
import co.develhope.loginsystem.user.entities.User;
import co.develhope.loginsystem.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/profile")
    public LoginRTO getProfile(Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        LoginRTO rto = new LoginRTO();
        rto.setUser(userService.convertToDTO(user));
        rto.setJWT(LoginService.getJWT(user));
        return rto;
    }

}
