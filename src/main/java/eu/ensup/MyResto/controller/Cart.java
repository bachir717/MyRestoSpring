package eu.ensup.MyResto.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller("/my_resto")
public class Cart {

    @GetMapping("/cart")
    public String viewHome(Model model) {
        return "cart";
    }

}
