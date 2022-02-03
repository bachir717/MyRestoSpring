package eu.ensup.MyResto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

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

    /*
    	@Autowired
	private ProduitService service;
	@PostMapping("/addProduit")
	public String addProduit(@RequestBody Produit produit)
	{
		return service.saveProduit(produit);
	}
	@GetMapping("/barChart")
	public String getAllProduit(Model model) {
	List<String> nameList= service.getAllProduit().stream().map(x->x.getName()).collect(Collectors.toList());
	List<Integer> priceList = service.getAllProduit().stream().map(x-> x.getPrice()).collect(Collectors.toList());
	model.addAttribute("name", nameList);
	model.addAttribute("price", priceList);
	return "barChart";
	}*/
}