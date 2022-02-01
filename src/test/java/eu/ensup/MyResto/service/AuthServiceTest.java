package eu.ensup.MyResto.service;

import eu.ensup.MyResto.domaine.Roles;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.repository.UserRepository;
import eu.ensup.MyResto.security.JwtProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Test sign up")
    public void testSignUp()
    {
        User user = new User();
        user.setUsername("admin");

        User userResult = new User();
        userResult.setUsername("admin");
        userResult.setPassword(authService.encodePassword("admin"));
        userResult.setRole(String.valueOf(Roles.USER));

        // GIVEN
        when(userRepository.save(user)).thenReturn(userResult);

        // WHEN
        final String result = authService.signup(user).toString();
        MatcherAssert.assertThat("Test fail : ", user.toString(), Matchers.equalTo(result));

        // THEN
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Test sign in")
    public void testSignIn() {
        User user = new User();
        user.setUsername("admin");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = token;
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String resultExpect = Jwts.builder().setSubject(user.getUsername()).signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512)).compact();

        // GIVEN
        when(authenticationManager.authenticate(token)).thenReturn(authenticate);
        when(jwtProvider.generateToken(authenticate)).thenReturn(resultExpect);

        // WHEN
        final String result = authService.signin(user);
        MatcherAssert.assertThat("Test fail : ", resultExpect, Matchers.equalTo(result));

        // THEN
        verify(authenticationManager).authenticate(token);
        verify(jwtProvider).generateToken(authenticate);
    }
}