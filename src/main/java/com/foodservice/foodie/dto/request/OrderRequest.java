package com.foodservice.foodie.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class OrderRequest {

    @NotNull(message = "User Id should not be null")
    private Long userId;

    @NotNull(message = "Restaurant Id should not be null")
    private Long restaurantId;

    @Valid
    private List<OrderItemRequest> orderItems;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
