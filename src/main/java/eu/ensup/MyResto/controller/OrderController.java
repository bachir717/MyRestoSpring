package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.domaine.User;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        if( session.getAttribute("ShoppingCard") != null ) {
            Map<Long, Integer> ids = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
            List<Product> list = new ArrayList<>();
            float total = 0f;
            for (Map.Entry<Long, Integer> entry :  ids.entrySet()) {
                for (int i =0; i< entry.getValue(); i++){
                    Product produit = productService.getOne(entry.getKey());
                    total += produit.getPrice();
                    list.add(productService.getOne(entry.getKey()));
                }
            }
            orderService.save(new Orders(total,new java.sql.Date(new Date().getTime()),null,list, States.CREATED,(User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
        }else{

        }
        return "redirect:/";
    }
}
