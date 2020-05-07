package com.april.beer.dto.form_object;

import javax.validation.constraints.NotEmpty;

public class ReviewFormObject {

    @NotEmpty
    private String reviewText;

    private String beerName;


    public ReviewFormObject() {
    }

    public ReviewFormObject(String message, String beerName) {
        this.reviewText = message;
        this.beerName = beerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

}
