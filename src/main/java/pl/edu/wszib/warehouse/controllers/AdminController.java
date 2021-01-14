package pl.edu.wszib.warehouse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.warehouse.model.Product;
import pl.edu.wszib.warehouse.model.User;
import pl.edu.wszib.warehouse.services.IProductService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/home";
        }
        model.addAttribute("product", this.productService.getProductByID(id));
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editSubmit(@ModelAttribute Product product){
        if(!this.productService.updateProduct(product)){
            return "redirect:/edit/{id}";
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/createNewProduct", method=RequestMethod.GET)
    public String addProductToDataBaseForm(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/home";
        }
        model.addAttribute("productModel", new Product());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/createNewProduct";
    }

    @RequestMapping(value = "/createNewProduct", method=RequestMethod.POST)
    public String addProductToDataBaseSubmit(@ModelAttribute Product product){
        if(!this.productService.createNewProduct(product)){
            return "redirect:/createNewProduct";
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/removeProduct", method=RequestMethod.GET)
    public String removeProductFromDataBaseForm(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/home";
        }
        model.addAttribute("productModel", new Product());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/removeProduct";
    }

    @RequestMapping(value = "/removeProduct", method=RequestMethod.POST)
    public String removeProductFromDataBaseSubmit(@ModelAttribute Product product){
        if(!this.productService.removeProductFromDataBase(product)) {
            return "redirect:/removeProduct";
        }

        return "redirect:/home";
    }
}
