package com.april.beer.controller;

import com.april.beer.dto.form_object.ReviewFormObject;
import com.april.beer.entity.Beer;
import com.april.beer.service.BeerService;
import com.april.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ReviewController {

    @Autowired
    BeerService beerService;

    @Autowired
    UserService userService;

    @PostMapping("/review")
    public ModelAndView postReview(@ModelAttribute("reviewFormObject") @Valid ReviewFormObject reviewFormObject, BindingResult result){
        Beer beer = beerService.findByName(reviewFormObject.getBeerName());

        beerService.addReview(beer, reviewFormObject.getReviewText());

        ModelAndView modelAndView = new ModelAndView("redirect:/search?beerName="+beer.getName());
        modelAndView.addObject("beer", beer);
        return modelAndView;
    }
}
