package com.icompete.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xhulio
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    
    @RequestMapping(value = "/home")
    public String home(Model model){
        
        model.addAttribute("message","hello World");
        
        return "home";
    }
    
}
