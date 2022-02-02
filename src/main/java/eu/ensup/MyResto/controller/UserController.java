package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.UserDTO;
import eu.ensup.MyResto.service.AuthService;
import eu.ensup.MyResto.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        log.info("loginPage");
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        log.info("save pour l'utilisateur "+user );
        if (!"".equals(user.getUsername()) && !"".equals(user.getPassword()))
            authService.signup(user);

        return "login";
    }
}
