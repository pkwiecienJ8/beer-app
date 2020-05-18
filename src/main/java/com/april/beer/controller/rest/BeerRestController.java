package com.april.beer.controller.rest;

import com.april.beer.entity.Beer;
import com.april.beer.exception.ResourceNotFoundException;
import com.april.beer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BeerRestController {

    @Autowired
    BeerService beerService;

    @GetMapping("/beers")
    public List<Beer> findAll() {
        return beerService.findAll();
    }

    @GetMapping("/beer/{id}")
    public ResponseEntity<Beer> findBeer(@PathVariable(value = "id") Long beerId) throws ResourceNotFoundException {
        Beer beer = beerService.findById(beerId).orElseThrow(() -> new ResourceNotFoundException("Beer not found, id:  " + beerId));
        return ResponseEntity.ok(beer);
    }

    @PostMapping("beer")
    public ResponseEntity<Beer> createBeer(@Valid @RequestBody Beer beer) {
        Beer createdBeer = beerService.save(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBeer);
    }

    @PutMapping("beer/{id}")
    public ResponseEntity<Object> updateBeer(@PathVariable(value = "id") Long beerId, @Valid @RequestBody Beer beerDetails) throws ResourceNotFoundException {
        beerService.update(beerId, beerDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("beer/{id}")
    public ResponseEntity<String> deleteBeer(@PathVariable(value = "id") Long beerId) throws ResourceNotFoundException {
        Beer beer = beerService.findById(beerId).orElseThrow(() -> new ResourceNotFoundException("Beer not found, id:  " + beerId));

        beerService.delete(beerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
