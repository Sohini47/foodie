package com.foodservice.foodie.controller;

import com.foodservice.foodie.dto.request.MenuItemRequest;
import com.foodservice.foodie.entity.MenuItem;
import com.foodservice.foodie.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<List<MenuItem>> createMenuItem(
            @PathVariable Long restaurantId, @RequestBody List<MenuItemRequest> menuItems) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuItemService.addMenuItemToRestaurant(restaurantId, menuItems));
    }

    @GetMapping
    public List<MenuItem> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuItemId}")
    public MenuItem getMenuItemById(@PathVariable Long restaurantId,  @PathVariable Long menuItemId) {
        return menuItemService.getMenuItemById(menuItemId, restaurantId);
    }

    @GetMapping("/search")
    public List<MenuItem> getMenuItemByName(@RequestParam String name, @PathVariable Long restaurantId) {
        return menuItemService.searchMenuItemByName(name, restaurantId);
    }
}
