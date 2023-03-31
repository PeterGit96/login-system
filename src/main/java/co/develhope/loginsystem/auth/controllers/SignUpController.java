package co.develhope.loginsystem.auth.controllers;

import co.develhope.loginsystem.auth.dto.SignUpActivationDTO;
import co.develhope.loginsystem.auth.dto.SignUpDTO;
import co.develhope.loginsystem.auth.services.SignUpService;
import co.develhope.loginsystem.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody SignUpDTO signUpDTO) {
        return signUpService.signUp(signUpDTO);
    }

    @PostMapping("/signup/activation")
    public UserDTO signup(@RequestBody SignUpActivationDTO signUpActivationDTO) {
        return signUpService.activate(signUpActivationDTO);
    }

}
