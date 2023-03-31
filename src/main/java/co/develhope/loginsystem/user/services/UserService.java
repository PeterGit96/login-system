package co.develhope.loginsystem.user.services;

import co.develhope.loginsystem.user.dto.UserDTO;
import co.develhope.loginsystem.user.entities.User;
import co.develhope.loginsystem.user.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User saveUser(@NotNull User user) {
        return userRepository.saveAndFlush(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByActivationCode(String activationCode) {
        return userRepository.findByActivationCode(activationCode);
    }

    public User findByPasswordResetCode(String passwordResetCode) {
        return userRepository.findByPasswordResetCode(passwordResetCode);
    }

    public User convertToEntity(@NotNull UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(@NotNull User user) {
        return modelMapper.map(user, UserDTO.class);
    }

}
