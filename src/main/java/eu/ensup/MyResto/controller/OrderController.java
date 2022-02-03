package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.Roles;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Log4j2
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/createsOrder")
    public String createsOrderHome(Model model, HttpSession session) throws ParseException {
        log.info("viewHome");

        if (session.getAttribute("ShoppingCard") != null) {
            Map<Long, Integer> ids = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
            List<Product> list = new ArrayList<>();
            float total = 0f;
            for (Map.Entry<Long, Integer> entry : ids.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {

                    Product produit = productService.getOne(entry.getKey());
                    total += produit.getPrice();
                    list.add(productService.getOne(entry.getKey()));
                }
            }
            orderService.save(new Orders(total, new java.sql.Date(new Date().getTime()), null, list, States.CREATED, (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
            session.removeAttribute("ShoppingCard");
        }
         else {
            session.setAttribute("error", "Aucun produit a commander");

            return "shoppingcard";
        }
        return "redirect:/";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@ModelAttribute Orders orders, HttpSession session) {

        Orders order = orderService.getOne(orders.getId());
        order.setSate(States.CANCELED);
        order.setDelivered(new java.sql.Date(new Date().getTime()));
        orderService.save(order);
        if (((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getRole() == Roles.USER)
            return "redirect:/myCommand";
        else
            return "redirect:/allCommand";

    }


    @GetMapping("/liveredOrder/{id}")
    public String liveredOrder(@ModelAttribute Orders orders, HttpSession session) {

        Orders order = orderService.getOne(orders.getId());
        order.setSate(States.DELIVERED);
        order.setDelivered(new java.sql.Date(new Date().getTime()));
        orderService.save(order);

        return "redirect:/allCommand";
    }

}
