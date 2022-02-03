package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.repository.UserRepository;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test load an user")
    void TestLoadUserByUsername() {
        User user = new User(0L,"name","LastName","email","address", Roles.USER,"mdp","",true,null);

        // GIVEN
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.of(user));

        // WHEN
        final String result = userService.loadUserByUsername(user.getUsername()).toString();
        MatcherAssert.assertThat("Test fail : ", result, Matchers.equalTo(user.toString()));

        // THEN
        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    @DisplayName("Test save an user")
    public void testSave() {
        User user = new User(0L,"name","LastName","email","address", Roles.USER,"mdp","",true,null);

        // GIVEN
        when(userRepository.save(user)).thenReturn(user);
        // WHEN
        MatcherAssert.assertThat("Test fail : ", userService.save(user));
        // THEN
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Test get one user by id")
    public void testGetOne()
    {
        User user = new User(0L,"name","LastName","email","address", Roles.USER,"mdp","",true,null);

        // GIVEN
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        // WHEN
        final User result = userService.getOne(user.getId());
        MatcherAssert.assertThat("Test fail : ", user.toString(), Matchers.equalTo(result.toString()));
        // THEN
        verify(userRepository).findById(user.getId());
    }
}