package com.april.beer.controller;

import com.april.beer.dto.form_object.BeerFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@ModelAttribute("beerFormObject") BeerFormObject beerFormObject){
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
