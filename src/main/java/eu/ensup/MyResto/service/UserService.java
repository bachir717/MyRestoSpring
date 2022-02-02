package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
    public boolean save(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user) != null;
    }
    public User getOne(Long userID) {
        User user = userRepository.findById(userID).orElse(null);
        if( user != null )
            user.setPassword(getEncodedPassword(user.getPassword()));

        return user;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
