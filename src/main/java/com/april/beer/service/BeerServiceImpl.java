package com.april.beer.service;

import com.april.beer.entity.Beer;
import com.april.beer.entity.Review;
import com.april.beer.entity.User;
import com.april.beer.exception.ObjectNotFoundInExternalApiException;
import com.april.beer.exception.ResourceNotFoundException;
import com.april.beer.repository.BeerRepository;
import com.april.beer.service.rest.BeerRestConsumer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
    public Review addReview(Beer beer, String reviewText) {
        Collection<Review> reviews = beer.getReviews();

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByEmail(userName);
        Review review = new Review(new Date(), reviewText, user);
        if (reviews == null) {
            beer.setReviews(Set.of(review));
        } else {
            reviews.add(review);
        }
        save(beer);

        return review;
    }

    @Override
    public List<Beer> findAll() {
        return Lists.newArrayList(beerRepository.findAll());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        beerRepository.deleteBeerById(id);
    }

    @Override
    public Beer update(Long id, Beer beer) throws ResourceNotFoundException {

        if(beerRepository.findById(id).isPresent()){
            Beer existingBeer = beerRepository.findById(id).get();

            existingBeer.setName(beer.getName());
            existingBeer.setDescription(beer.getDescription());

            return beerRepository.save(existingBeer);
        } else {
            throw new ResourceNotFoundException("Cannot find beer, id " + id);
        }
    }
}
