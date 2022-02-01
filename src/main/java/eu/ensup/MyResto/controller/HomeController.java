package eu.ensup.MyResto.controller;


import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.service.AuthService;
import eu.ensup.MyResto.service.OpinionsService;
import eu.ensup.MyResto.service.ProductService;
import eu.ensup.MyResto.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OpinionsService opinionsService;


    @GetMapping("/")
    public String viewHome(Model model) {
        log.info("viewHome");

        return "home";
    }
}
