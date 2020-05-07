package com.april.beer.service;

import com.april.beer.entity.Beer;
import com.april.beer.entity.Review;
import com.april.beer.entity.User;
import com.april.beer.exception.ObjectNotFoundInExternalApiException;
import com.april.beer.repository.BeerRepository;
import com.april.beer.service.rest.BeerRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerRestConsumer beerRestConsumer;


    @Autowired
    UserService userService;

    @Override
    public Beer findByName(String beerName) {
        Beer beer = beerRepository.findByName(beerName);
        if (beer == null) {
            try {
                beer = beerRestConsumer.findByName(beerName);
            } catch (ObjectNotFoundInExternalApiException e) {
                //TODO add exception handling and redirect to creating new beer
            }
        }
        return beer;
    }

    @Override
    public Beer save(Beer beer) {
        return beerRepository.save(beer);
    }

    @Override
    public Optional<Beer> findById(Long id) {
        return beerRepository.findById(id);
    }

    @Override
    public void addReview(Beer beer, String review) {
        Collection<Review> reviews = beer.getReviews();

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByEmail(userName);
        if (reviews == null) {
            beer.setReviews(Set.of(new Review(new Date(), review, user)));
        } else {
            reviews.add(new Review(new Date(), review, user));
        }

        save(beer);
    }
}
