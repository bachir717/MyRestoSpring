package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GraphstatController {
    @Autowired
    private ProductService productService ;
    @Autowired
    private OrderService orderService ;

    public String etatOrder(Model model)
    {
        int nbProductDelivered = 0;
        int nbProductCreated = 0;
        int nbProductConceld = 0;
        List<Orders> orders = (List<Orders>) orderService.getAll();
        for(Orders order: orders){
            if(order.getSate() == States.DELIVERED)
                nbProductDelivered++;
            if(order.getSate() == States.CREATED)
                nbProductCreated++;
            if(order.getSate() == States.CANCELED)
                nbProductConceld++;
        }
        //model.addAttribute("nbProductDelivered ",nbProductDelivered);
        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        data.put("nbProductDelivered", nbProductDelivered);
        data.put("nbProductCreated", nbProductCreated);
        data.put("nbProductConceld", nbProductConceld);
        //data.put("Totale des produit", orders.size());
        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        return "ok";
    }

    public String ca(Model model)
    {
        Map<String, Float> data = new LinkedHashMap<>();
        List<Orders> orders = (List<Orders>) orderService.getAll();
        for(Orders order: orders){
            if(order.getSate() == States.DELIVERED)
            {
                Float value = data.get(order.getCreated().toString());
                data.put(order.getCreated().toString(), (value == null ? 0F : value ) + order.getPrice());
            }
        }
        model.addAttribute("key", data.keySet());
        model.addAttribute("val", data.values());
        return "ok";
    }

    @GetMapping("/Stats")
    public String Stat(Model model)
    {
        ca( model);
        etatOrder( model);
        return "PageStatistiques";
    }



}
