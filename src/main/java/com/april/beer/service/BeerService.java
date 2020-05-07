package com.april.beer.service;

import com.april.beer.entity.Beer;

import java.util.Optional;

public interface BeerService {
    Beer findByName(String beerName);

    Beer save(Beer beer);

    Optional<Beer> findById(Long id);

    void addReview(Beer beer, String review);
}
