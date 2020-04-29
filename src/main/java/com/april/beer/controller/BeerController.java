package com.april.beer.controller;

import com.april.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeerController {

    @Autowired
    BeerRepository beerRepository;

    @GetMapping("/beers")
    public String beers(Model model){
        model.addAttribute("beers", beerRepository.findAll());
        return "beer-list";
    }
}
