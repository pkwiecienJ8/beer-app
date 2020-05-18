package com.april.beer.service;

import com.april.beer.entity.Beer;
import com.april.beer.entity.Review;
import com.april.beer.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BeerService {
    Beer findByName(String beerName);

    Beer save(Beer beer);

    Optional<Beer> findById(Long id);

    Review addReview(Beer beer, String review);

    List<Beer> findAll();

    void delete(Long id);

    Beer update(Long id, Beer beer) throws ResourceNotFoundException;
}
