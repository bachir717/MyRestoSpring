package eu.ensup.MyResto.controller;


import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.repository.UserRepository;
import eu.ensup.MyResto.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static eu.ensup.MyResto.model.Types.*;

@Log4j2
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OpinionsService opinionsService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String viewHome(Model model) {
        log.info("viewHome");


        List<Product> entrees  = new ArrayList<Product>(){ };
        List<Product> plats  = new ArrayList<Product>(){ };
        List<Product> desserts  = new ArrayList<Product>(){ };
        List<Product> boissons = new ArrayList<Product>(){ };

        for (Product produc : productService.getAll()) {
            switch (produc.getType()) {
                case BOISSON:
                    boissons.add(produc);
                    break;
                case ENTREE:
                    entrees.add(produc);
                    break;
                case PLAT:
                    plats.add(produc);
                    break;
                case DESSERT:
                    desserts.add(produc);
                    break;
                default:
                    break;
            }
        }
       // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       // orderService.save(new Orders(12.1f,null,null,entrees, States.CREATED,(User)(principal)));

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", new User());
        model.addAttribute("entrees",  entrees);
        model.addAttribute("plats",  plats);
        model.addAttribute("desserts",  desserts);
        model.addAttribute("boissons",  boissons);
        return "home";
    }


    @GetMapping("/users")
    public String listUser(Model model) {
        log.info("listUser");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User)principal);
        if (user.getRole() == Roles.GERANT){
            List<User> users = userRepository.findAll().stream()
                    .filter(u->u.getActivate()==true && u.getRole() == Roles.USER)
                    .collect(Collectors.toList());

            model.addAttribute("users", users);
            return "userList";
        }else
            return "error";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Model model, @RequestParam(value = "id", required = true) Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User)principal);
        if (user.getRole() == Roles.GERANT){
            User deletedUser=userRepository.findById(id).get();
            if(deletedUser != null){
                deletedUser.setActivate(false);
                userRepository.save(deletedUser);
            }

            return listUser(model);
        }else
            return "error";

    }

}
