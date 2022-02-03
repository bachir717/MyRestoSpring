package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.UserDTO;
import eu.ensup.MyResto.service.AuthService;
import eu.ensup.MyResto.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String viewCreateUserPage(Model model) {
        log.info("viewCreateUserPage");
        model.addAttribute("user", new User());
        return "createdUser";
    }

    @GetMapping("/login")
    public String loginPage(Model model,HttpSession session,@RequestParam(value = "error", defaultValue = "false") boolean loginError) {
        log.info("loginPage");
        session.removeAttribute("error");
        model.addAttribute("user", new User());
        if (loginError) {
            session.setAttribute("error", "Mauvais login ou mot de passe!");
        }
        return "login";
    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        log.info("loginPage");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User)principal);
        model.addAttribute("user", user);

        return "updateUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user,HttpSession session) {
        log.info("save pour l'utilisateur "+user );
        user.setActivate(true);
        if (!"".equals(user.getUsername()) && !"".equals(user.getPassword())  && !"".equals(user.getAddress())  && !"".equals(user.getLastName()))
            if (validate(user.getEmail()))
                if (user.getPassword().split(",")[0].equals(user.getPassword().split(",")[1]))
                    if (userService.loadUserByUsername(user.getUsername()) == null)
                    {
                        user.setPassword(user.getPassword().split(",")[0]);
                        authService.signup(user);
                        session.removeAttribute("error");

                        return "login";
                    }
                    else
                        session.setAttribute("error", "L'utilistateur est déjà crée");
                else
                    session.setAttribute("error", "Les mot de passe ne sont pas identiques");
            else
                session.setAttribute("error", "L'adresse mail n'est pas sous le bon format");
        else
            session.setAttribute("error", "Tout les champs ne sont pas remplis");

        return "createdUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,HttpSession session) {
        log.info("update pour l'utilisateur "+user );
        User userload = ((User)userService.loadUserByUsername(user.getUsername()));
        user.setId(userload.getId());
        user.setPassword(userload.getPassword());
        user.setRole(userload.getRole());
        userService.save(user);
        return "redirect:/";
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
