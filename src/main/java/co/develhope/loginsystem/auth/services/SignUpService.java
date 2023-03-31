package co.develhope.loginsystem.auth.services;

import co.develhope.loginsystem.auth.dto.SignUpActivationDTO;
import co.develhope.loginsystem.auth.dto.SignUpDTO;
import co.develhope.loginsystem.notification.services.MailNotificationService;
import co.develhope.loginsystem.user.dto.UserDTO;
import co.develhope.loginsystem.user.entities.User;
import co.develhope.loginsystem.user.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignUpService {

    @Autowired
    private UserService userService;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO signUp(@NotNull SignUpDTO signUpDTO) {
        User userFromDB = userService.findByEmail(signUpDTO.getEmail());
        if(userFromDB != null) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setSurname(signUpDTO.getSurname());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setActive(false);
        user.setActivationCode(UUID.randomUUID().toString());

        mailNotificationService.sendActivationMail(user);
        return userService.convertToDTO(userService.saveUser(user));
    }

    public UserDTO activate(@NotNull SignUpActivationDTO signUpActivationDTO) {
        User userFromDB = userService.findByActivationCode(signUpActivationDTO.getActivationCode());
        if(userFromDB == null) {
            throw new EntityNotFoundException("User not found");
        }
        userFromDB.setActive(true);
        userFromDB.setActivationCode(null);
        return userService.convertToDTO(userService.saveUser(userFromDB));
    }

}
