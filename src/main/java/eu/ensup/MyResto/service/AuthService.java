package eu.ensup.MyResto.service;

import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.UserDTO;
import eu.ensup.MyResto.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public User signup(User user) {
        user.setRole(Roles.USER);
        User result = userRepository.save(user);
        result.setPassword(passwordEncoder.encode(user.getPassword()));
        return result;
    }

    public User signin(User user) {
        User result = userRepository.findByUsername(user.getUsername()).get();
        result.setPassword(passwordEncoder.encode(user.getPassword()));
        return result;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
