package com.foodservice.foodie.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RestaurantRequest {

    @NotNull(message = "Restaurant name cannot be null")
    private String name;

    @NotNull(message = "Restaurant location cannot be null")
    private String location;

    private Double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
