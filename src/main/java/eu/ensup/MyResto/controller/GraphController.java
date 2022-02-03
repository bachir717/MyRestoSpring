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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GraphController {

    @GetMapping("/barChart")
    public String barChart(Model model)
    {
        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        data.put("AD", 30);
        data.put("BL", 50);
        data.put("GP", 70);
        data.put("MO", 90);
        data.put("KN", 25);
        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        return "barChart";

    }

    @GetMapping("/pieChart")
    public String pieChart(Model model) {
        model.addAttribute("pass", 90);
        model.addAttribute("fail", 10);
        return "pieChart";

    }

    @Autowired
	private ProductService productService ;
    @Autowired
    private OrderService  orderService ;
	@GetMapping("/ListProduit")
	public String addProduit(Model model)
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
        return "barChart";
	}
//	 @GetMapping("/barChart")
//	public String getAllProduit(Model model) {
//	List<String> nameList= service.getAllProduit().stream().map(x->x.getName()).collect(Collectors.toList());
//	List<Integer> priceList = service.getAllProduit().stream().map(x-> x.getPrice()).collect(Collectors.toList());
//	model.addAttribute("name", nameList);
//	model.addAttribute("price", priceList);
//	return "barChart";
//	}
}