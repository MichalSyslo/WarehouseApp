package pl.edu.wszib.warehouse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.warehouse.services.IProductService;
import pl.edu.wszib.warehouse.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(){

        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        return "/home";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "/contact";
    }


}
