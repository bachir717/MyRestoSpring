package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.repository.UserRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Test sign up")
    public void testSignUp()
    {
        User user = new User(0L,"name","LastName","email","address", Roles.USER,"mdp","",null,null);

        User resu = new User();
        resu.setUsername(user.getUsername());
        resu.setPassword("admin");
        resu.setRole(Roles.USER);

        // GIVEN
        when(userRepository.save(user)).thenReturn(resu);

        // WHEN
        final String result = authService.signup(user).toString();
        MatcherAssert.assertThat("Test fail : ", resu.toString(), Matchers.equalTo(result));

        // THEN
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Test sign in")
    public void testSignIn()
    {
        User user = new User(0L,"name","LastName","email","address", Roles.USER,"mdp","",null,null);

        // GIVEN
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // WHEN
        final String result = authService.signin(user).toString();
        MatcherAssert.assertThat("Test fail : ", user.toString(), Matchers.equalTo(result));

        // THEN
        verify(userRepository).findByUsername(user.getUsername());
    }
}
