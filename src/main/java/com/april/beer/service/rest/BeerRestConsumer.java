package com.april.beer.service.rest;

import com.april.beer.dto.json.BeerJson;
import com.april.beer.entity.Beer;
import com.april.beer.exception.ObjectNotFoundInExternalApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BeerRestConsumer {
    private static final String EXTERNAL_BEER_API = "https://api.punkapi.com/v2/beers?beer_name=";

    @Autowired
    RestTemplate restTemplate;

    public Beer findByName(String beerName) throws ObjectNotFoundInExternalApiException {
        ResponseEntity<BeerJson[]> response = restTemplate.getForEntity(EXTERNAL_BEER_API + beerName, BeerJson[].class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody().length <= 0) {
            throw new ObjectNotFoundInExternalApiException("Object was not found at " + EXTERNAL_BEER_API + beerName);
        }

        //TODO API returning list, even when we want only one object, maybe there is better solution
        BeerJson[] beers = response.getBody();
        BeerJson beerJson = beers[0];

        //TODO know we are adding object from eternal api to our DB, when we search for it, in future add it only when it will be rated
        Beer beer = new Beer();
        beer.setName(beerJson.getName());
        beer.setDescription(beerJson.getDescription());
        beer.setAbv(beerJson.getAbv());
        beer.setFirstBrewed(beerJson.getFirstBrewed());
        beer.setIbu(beerJson.getIbu());
        beer.setImageUrl(beerJson.getImageUrl());

        return beer;
    }
}
