package com.april.beer.dto.form_object;

import javax.validation.constraints.NotEmpty;

public class ReviewFormObject {

    @NotEmpty
    private String comment;

    private String beerName;


    public ReviewFormObject() {
    }

    public ReviewFormObject(String beerName) {
        this.beerName = beerName;
    }

    public ReviewFormObject(@NotEmpty String comment, String beerName) {
        this.comment = comment;
        this.beerName = beerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

}
