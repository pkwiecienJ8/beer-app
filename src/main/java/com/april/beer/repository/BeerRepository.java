package com.april.beer.repository;

import com.april.beer.entity.Beer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Long> {
    Beer findByName(String name);
}
