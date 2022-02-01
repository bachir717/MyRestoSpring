package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.User;
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

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test load an user who exist return an user")
    void TestLoadUserByUsername() {
        User user = new User();
        user.setUsername("admin");

        // GIVEN
        when(userRepository.findByUsername("admin")).thenReturn(java.util.Optional.of(user));

        // WHEN
        final String result = userService.loadUserByUsername("admin").toString();
        MatcherAssert.assertThat("Test fail : ", result, Matchers.equalTo(user.toString()));

        // THEN
        verify(userRepository).findByUsername("admin");
    }

    @Test
    @DisplayName("Test load an user who does not exist return an exception")
    public void TestExceptionLoadUserByUsername() {
        // GIVEN
        when(userRepository.findByUsername("admin")).thenReturn(Optional.empty());

        // WHEN
        var result = Assertions.catchThrowable(() -> userService.loadUserByUsername("admin"));
        Assertions.assertThat(result).isInstanceOf(UsernameNotFoundException.class).hasMessage("Username not found: admin");

        // THEN
        verify(userRepository).findByUsername("admin");
    }
}