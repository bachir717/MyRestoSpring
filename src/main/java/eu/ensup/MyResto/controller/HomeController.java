package eu.ensup.MyResto.controller;


import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.model.Types;
import eu.ensup.MyResto.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static eu.ensup.MyResto.model.Types.*;

@Log4j2
@Controller
public class HomeController {

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
        orderService.save(new Orders(12.1f,null,null,entrees, States.CREATED,userService.getOne(1l).get()));

        model.addAttribute("user", new User());
        model.addAttribute("entrees",  entrees);
        model.addAttribute("plats",  plats);
        model.addAttribute("desserts",  desserts);
        model.addAttribute("boissons",  boissons);

        return "home";
    }
}
