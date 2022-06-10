package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.Orders;
import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.model.States;
import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GraphController {

    @Autowired
	private ProductService productService ;
    @Autowired
    private OrderService  orderService ;
//	@GetMapping("/ListProduit")
//	public String etatOrderB(Model model)
//	{
//        int nbProductDelivered = 0;
//        int nbProductCreated = 0;
//        int nbProductConceld = 0;
//        List<Orders> orders = (List<Orders>) orderService.getAll();
//        for(Orders order: orders){
//            if(order.getSate() == States.DELIVERED)
//                nbProductDelivered++;
//            if(order.getSate() == States.CREATED)
//                nbProductCreated++;
//            if(order.getSate() == States.CANCELED)
//                nbProductConceld++;
//        }
//        //model.addAttribute("nbProductDelivered ",nbProductDelivered);
//        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
//        data.put("nbProductDelivered", nbProductDelivered);
//        data.put("nbProductCreated", nbProductCreated);
//        data.put("nbProductConceld", nbProductConceld);
//        //data.put("Totale des produit", orders.size());
//        model.addAttribute("keySet", data.keySet());
//        model.addAttribute("values", data.values());
//        return "orderChart";
//	}
//
//    @GetMapping("/ChiffreAffaireChart")
//    public String chiffreAffaire(Model model)
//    {
//        Map<String, Float> data = new LinkedHashMap<>();
//        List<Orders> orders = (List<Orders>) orderService.getAll();
//        for(Orders order: orders){
//            if(order.getSate() == States.DELIVERED)
//            {
//                Float value = data.get(order.getCreated().toString());
//                data.put(order.getCreated().toString(), (value == null ? 0F : value ) + order.getPrice());
//            }
//        }
//        model.addAttribute("keySet", data.keySet());
//        model.addAttribute("values", data.values());
//        return "prixChart";
//    }



//    private List<List<Object>> convertMapFloatInListFloat(Map<Long, Float> mapObject)
//    {
//        List<List<Object>> listObject = new ArrayList<>();
//        mapObject.forEach((key, value)->listObject.add(Arrays.asList(key,value)));
//        return listObject;
//    }
    private List<List<Object>> convertMapFloatInListFloat(Map<Long, Float> mapObject)
    {
        System.out.println("etap1");
        List<List<Object>> listObject = new ArrayList<>();
        for(Long key : mapObject.keySet()) listObject.add(Arrays.asList(key,mapObject.get(key)));
        System.out.println(listObject.size() + " " + mapObject.keySet().size());
        return listObject;
    }
    @GetMapping("/StarsChartC")
    public String courb(Model model){
        Map<Long, Float> mapCreated = new LinkedHashMap<>();
        Map<Long, Float> mapDelivered = new LinkedHashMap<>();
        List<Orders> orders = (List<Orders>) orderService.getAll();
        for(Orders order: orders){
            Date dateCreated = order.getCreated();
            if(order.getSate() == States.CREATED && dateCreated != null)
            {
                Float total = 0F;
                if(mapCreated.get(dateCreated) != null)
                    total = mapCreated.get(dateCreated);

                total = total + (order.getPrice() != null ? order.getPrice() : 0F);

                mapCreated.put(dateCreated.getTime(), total);
            }
            Date dateDelivered = order.getDelivered();
            if(order.getSate() == States.DELIVERED && dateDelivered != null)
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
        return "StatsChart";
    }
    //@GetMapping("/DatePrix")
//    public String datePrix(Model model)
//    {
//        Map<String, Float> data = new LinkedHashMap<>();
//        List<Orders> orders = (List<Orders>) orderService.getAll();
//        for(Orders order: orders){
//            if(order.getSate() == States.DELIVERED)
//            {
//                Float value = data.get(order.getCreated().toString());
//                data.put(order.getCreated().toString(), (value == null ? 0F : value ) + order.getPrice());
//            }
//        }
//        model.addAttribute("keySet", data.keySet());
//        model.addAttribute("values", data.values());
//        return "prixChart";
//    }


}