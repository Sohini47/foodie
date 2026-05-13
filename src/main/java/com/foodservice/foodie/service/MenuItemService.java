package com.foodservice.foodie.service;

import com.foodservice.foodie.dto.request.MenuItemRequest;
import com.foodservice.foodie.entity.MenuItem;
import com.foodservice.foodie.entity.Restaurant;
import com.foodservice.foodie.exception.ResourceNotFoundException;
import com.foodservice.foodie.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<MenuItem> addMenuItemToRestaurant(Long id, List<MenuItemRequest> menuItems) {
        Restaurant restaurant = restaurantService.findById(id);
        List<MenuItem> menuItemList = menuItems
                .stream().map(menuItem ->{
                    MenuItem m = new MenuItem();
                    m.setRestaurant(restaurant);
                    m.setName(menuItem.getName());
                    m.setPrice(menuItem.getPrice());
                    return m;
                }).toList();
        return menuItemRepository.saveAll(menuItemList);
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
