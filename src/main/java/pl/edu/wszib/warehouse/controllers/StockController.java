package pl.edu.wszib.warehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.warehouse.model.Product;
import pl.edu.wszib.warehouse.services.IProductService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class StockController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addProduct/{id}", method = RequestMethod.GET)
    public String addProductForm(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/home";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("productModel", new Product(id, "", 0));
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/addProduct";
    }

    @RequestMapping(value = "/addProduct/{id}", method = RequestMethod.POST)
    public String addProductSubmit(@ModelAttribute Product product){
        if(!this.productService.addProduct(product)){
            return "redirect:/addProduct/{id}";
        }

        return "redirect:/home";
    }


    @RequestMapping(value = "/subtractProduct/{id}", method = RequestMethod.GET)
    public String subtractProductForm(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/home";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("productModel", new Product(id, "", 0));
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/subtractProduct";
    }

    @RequestMapping(value = "/subtractProduct/{id}", method = RequestMethod.POST)
    public String subtractProductSubmit(@ModelAttribute Product product){
        if(!this.productService.subtractProduct(product)){
            return "redirect:/subtractProduct/{id}";
        }

        return "redirect:/home";
    }
}
