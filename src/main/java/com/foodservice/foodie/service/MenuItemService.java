package com.foodservice.foodie.service;

import com.foodservice.foodie.entity.MenuItem;
import com.foodservice.foodie.entity.Restaurant;
import com.foodservice.foodie.exception.ResourceNotFoundException;
import com.foodservice.foodie.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<MenuItem> addMenuItemToRestaurant(Long id, List<MenuItem> menuItems) {
        Restaurant restaurant = restaurantService.findById(id);
        menuItems.forEach(menuItem -> menuItem.setRestaurant(restaurant));
        return menuItemRepository.saveAll(menuItems);
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem getMenuItemById(Long restaurantId, Long menuItemId) {
        return menuItemRepository.findByIdAndRestaurant_Id(menuItemId, restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException(MenuItem.class.getSimpleName(), "id", menuItemId));
    }

    public List<MenuItem> searchMenuItemByName(String name, Long restaurantId) {
        return menuItemRepository.findByNameAndRestaurantIdContainingIgnoreCase(name, restaurantId);
    }
}
