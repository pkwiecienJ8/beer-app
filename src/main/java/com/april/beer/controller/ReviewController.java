package com.april.beer.controller;

import com.april.beer.dto.form_object.ReviewFormObject;
import com.april.beer.entity.Beer;
import com.april.beer.entity.Review;
import com.april.beer.service.BeerService;
import com.april.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    BeerService beerService;

    @Autowired
    UserService userService;

    @PostMapping("/review")
    public @ResponseBody Review addComment(@RequestBody ReviewFormObject reviewFormObject){
        Beer beer = beerService.findByName(reviewFormObject.getBeerName());
        Review review = beerService.addReview(beer, reviewFormObject.getComment());
        return review;
    }

    @GetMapping("/reviews")
    @ResponseBody
    public List<String> reviews() {
        return List.of("test", "super");
    }
}
