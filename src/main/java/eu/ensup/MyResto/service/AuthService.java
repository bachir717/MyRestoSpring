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
        return userRepository.save(mapToEntity(user, new User()));
    }

    public UserDTO signin(User user) {
        return modelMapper.map(userRepository.findByUsername(user.getUsername()).get(),UserDTO.class);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private User mapToEntity(final User userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodePassword(userDTO.getPassword()));
        user.setRole(Roles.USER);
        return user;
    }


}
