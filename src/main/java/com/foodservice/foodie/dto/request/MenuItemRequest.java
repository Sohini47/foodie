package com.foodservice.foodie.dto.request;

import jakarta.validation.constraints.NotNull;

public class MenuItemRequest {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Restaurant Id cannot be null")
    private Long restaurantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
