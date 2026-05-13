package com.foodservice.foodie.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class OrderItemRequest {

    @NotNull(message = "MenuItemId should not be null")
    private Long menuItemId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity should not be less than 1")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }
}
