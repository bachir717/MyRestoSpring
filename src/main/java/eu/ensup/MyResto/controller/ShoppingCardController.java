package eu.ensup.MyResto.controller;

import eu.ensup.MyResto.domaine.Product;
import eu.ensup.MyResto.service.OpinionsService;
import eu.ensup.MyResto.service.OrderService;
import eu.ensup.MyResto.service.ProductService;
import eu.ensup.MyResto.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
public class ShoppingCardController{
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addProductShoppingCard/{id}")
    public String addShoppingCard(@PathVariable("id") Long id, Model model, HttpSession session)
    {
        log.info("addShoppingCard");
        Map<Long, Integer> productIds = new HashMap<>();
        if( session.getAttribute("ShoppingCard") == null )
        {
            productIds.put(id, 1);
            session.setAttribute("ShoppingCard",productIds);
        }
        else
        {
            productIds = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
            if( productIds.containsKey(id) )
                productIds.put(id, productIds.get(id)+1);
            else
                productIds.put(id, 1);
            session.setAttribute("ShoppingCard",productIds);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/shoppingcard")
    public String addShoppingCard(Model model, HttpSession session)
    {
        log.info("addShoppingCard");
        Map<Product, Integer> products = new HashMap<>();

        if( session.getAttribute("ShoppingCard") != null ) {
            Map<Long, Integer> ids = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
            for(Long id : ids.keySet() )
                products.put(productService.getOne(id), ids.get(id));
        }
        model.addAttribute("products", products);
        return "shoppingCard";
    }

    @RequestMapping(value = "/shoppingcard/more/{id}")
    public String moreProduct(@PathVariable("id") Long id,  Model model, HttpSession session)
    {
        log.info("moreProduct");
        Map<Long, Integer> mapProduct = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
        if( mapProduct != null && ! mapProduct.isEmpty() && mapProduct.keySet().contains(id) )
        {
            mapProduct.put(id, mapProduct.get(id)+1);
            session.setAttribute("ShoppingCard",mapProduct);
        }

        return "redirect:/shoppingcard";
    }

    @RequestMapping(value = "/shoppingcard/less/{id}")
    public String lessProduct(@PathVariable("id") Long id,  Model model, HttpSession session)
    {
        log.info("lessProduct");
        Map<Long, Integer> mapProduct = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
        if( mapProduct != null && ! mapProduct.isEmpty() && mapProduct.keySet().contains(id) )
        {
            int number = mapProduct.get(id)-1;
            if( number > 0 )  mapProduct.put(id, number);
            else              mapProduct.remove(id);

            session.setAttribute("ShoppingCard",mapProduct);
        }

        return "redirect:/shoppingcard";
    }

    @RequestMapping(value = "/shoppingcard/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id,  Model model, HttpSession session)
    {
        log.info("removeProduct");
        Map<Long, Integer> mapProduct = (Map<Long, Integer>) session.getAttribute("ShoppingCard");
        if( mapProduct != null && ! mapProduct.isEmpty() && mapProduct.keySet().contains(id) )
        {
            mapProduct.remove(id);
            session.setAttribute("ShoppingCard",mapProduct);
        }

        return "redirect:/shoppingcard";
    }
}
