package com.foodservice.foodie.dto;

//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Min;

public class OrderItemRequest {

    private Long menuItemId;

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
