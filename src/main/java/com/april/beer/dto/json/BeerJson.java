package com.april.beer.dto.json;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

public class BeerJson implements Serializable {
    private String name;
    private String description;
    private String imageUrl;
    private String ibu;
    private String firstBrewed;
    private String abv;


    public BeerJson() {
    }

    public BeerJson(String name, String description, String imageUrl, String ibu, String firstBrewed, String abv) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ibu = ibu;
        this.firstBrewed = firstBrewed;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter(value = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    @JsonGetter(value = "first_brewed")
    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }
}
