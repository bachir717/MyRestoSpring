package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Log4j2
@Controller
public class GerantController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/allCommand")
    public String allCommandGerant(Model model, HttpSession session) throws ParseException {
       var allorders =  orderService.getAll();
        model.addAttribute("allcommands",allorders);
        return "list-commands";
    }
}
