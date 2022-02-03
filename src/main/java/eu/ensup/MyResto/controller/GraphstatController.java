package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

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
        data.put("Commandes livrées", nbProductDelivered);
        data.put("Commandes ajoutées", nbProductCreated);
        data.put("Commandes annullées", nbProductConceld);
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

    private List<List<Object>> convertMapFloatInListFloat(Map<Long, Float> mapObject)
    {
        List<List<Object>> listObject = new ArrayList<>();
        mapObject.forEach((key, value)->listObject.add(Arrays.asList(key,value)));
        return listObject;
    }

    public String courb(Model model){
        Map<Long, Float> mapCreated = new LinkedHashMap<>();
        Map<Long, Float> mapDelivered = new LinkedHashMap<>();
        List<Orders> orders = (List<Orders>) orderService.getAll();
        for(Orders order: orders){
            Date dateCreated = order.getCreated();
            if(order.getSate() != States.CANCELED && dateCreated != null)
            {
                Float total = 0F;
                if(mapCreated.get(dateCreated) != null)
                    total = mapCreated.get(dateCreated);

                total = total + (order.getPrice() != null ? order.getPrice() : 0F);

                mapCreated.put(dateCreated.getTime(), total);
            }
            Date dateDelivered = order.getDelivered();
            if(order.getSate() != States.CANCELED && dateDelivered != null)
            {
                Float total = 0F;
                if(mapDelivered.get(dateDelivered.getMonth()) != null)
                    total = mapDelivered.get(dateDelivered.getMonth());

                total = (total != null ? total : 0F) + (order.getPrice() != null ? order.getPrice() : 0F);

                mapDelivered.put(dateDelivered.getTime(), total);
            }
        }

        model.addAttribute("mapCreated", convertMapFloatInListFloat(mapCreated));
        model.addAttribute("mapDelivered", convertMapFloatInListFloat(mapDelivered));
        return "ok";
    }

    @GetMapping("/Stats")
    public String Stat(Model model)
    {
        ca( model);
        etatOrder( model);
        courb(model);
        return "PageStatistiques";
    }



}
