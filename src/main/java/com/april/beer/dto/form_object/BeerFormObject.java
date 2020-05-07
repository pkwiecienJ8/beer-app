package com.april.beer.dto.form_object;

import javax.validation.constraints.NotEmpty;

public class BeerFormObject {

    @NotEmpty
    private String beerName;

    public BeerFormObject() {
    }

    public BeerFormObject(@NotEmpty String beerName) {
        this.beerName = beerName;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }
}
