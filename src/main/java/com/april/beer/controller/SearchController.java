package com.april.beer.controller;

import com.april.beer.dto.form_object.BeerFormObject;
import com.april.beer.entity.Beer;
import com.april.beer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SearchController {

    @Autowired
    BeerService beerService;

    @GetMapping("/search")
    public ModelAndView searchBeer(@ModelAttribute("beerFormObject") @Valid BeerFormObject beerFormObject,
                                   BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("/index");
        }

        Beer beer = beerService.findByName(beerFormObject.getBeerName());

        ModelAndView modelAndView = new ModelAndView("/beer-search-result");
        modelAndView.addObject("beer", beer);
        return modelAndView;
    }
}
