package com.foodservice.foodie.service;

import com.foodservice.foodie.dto.request.OrderItemRequest;
import com.foodservice.foodie.dto.response.OrderItemResponse;
import com.foodservice.foodie.dto.request.OrderRequest;
import com.foodservice.foodie.dto.response.OrderResponse;
import com.foodservice.foodie.dto.response.UserResponse;
import com.foodservice.foodie.entity.*;
import com.foodservice.foodie.enums.Status;
import com.foodservice.foodie.exception.ResourceNotFoundException;
import com.foodservice.foodie.repository.OrderItemRepository;
import com.foodservice.foodie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @Transactional
    public Order createOrder(OrderRequest request) {
        if(request.getOrderItems()==null || request.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one order item");
        }
        User user = userService.getUserEntityById(request.getUserId());
        Restaurant restaurant = restaurantService.findById(request.getRestaurantId());

        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setRestaurant(restaurant);

        double total = 0;

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getOrderItems()) {
            MenuItem menuItem = menuItemService.getMenuItemById(request.getRestaurantId(), itemRequest.getMenuItemId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setMenuItem(menuItem);
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setQuantity(itemRequest.getQuantity());

            total+=orderItem.getPrice()*itemRequest.getQuantity();

            orderItems.add(orderItem);
        }
        newOrder.setTotalAmount(total);
        newOrder.setStatus(Status.ORDER_PLACED);

        Order savedOrder = orderRepository.save(newOrder);

        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }

    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException(Order.class.getSimpleName(), "id", orderId));
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setStatus(order.getStatus());
        response.setTotal(order.getTotalAmount());
        response.setUserId(order.getUser().getId());
        response.setRestaurantId(order.getRestaurant().getId());

        List<OrderItemResponse> itemResponses = getItemResponses(orderItems);
        response.setItems(itemResponses);

        return response;
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderItem> orderItemsList = orderItemRepository.findAll();
        Map<Long, List<OrderItem>> itemsByOrderId = orderItemsList.stream()
                .collect(Collectors.groupingBy(item -> item.getOrder().getId()));
        List<OrderResponse> responses = new ArrayList<>();

        for(Order order: orders){
            OrderResponse response = new OrderResponse();
            Long orderId = order.getId();
            response.setOrderId(orderId);
            response.setStatus(order.getStatus());
            response.setTotal(order.getTotalAmount());
            response.setUserId(order.getUser().getId());
            response.setRestaurantId(order.getRestaurant().getId());

            List<OrderItem> orderItems = itemsByOrderId.getOrDefault(orderId, new ArrayList<>());

            List<OrderItemResponse> itemResponses = getItemResponses(orderItems);
            response.setItems(itemResponses);

            responses.add(response);
        }
        return responses;
    }

    public List<OrderItemResponse> getItemResponses(List<OrderItem> orderItems){
        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for(OrderItem item: orderItems){
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setMenuItemId(item.getMenuItem().getId());
            itemResponse.setName(item.getMenuItem().getName());
            itemResponse.setPrice(item.getMenuItem().getPrice());
            itemResponse.setQuantity(item.getQuantity());

            itemResponses.add(itemResponse);
        }
        return itemResponses;
    }

}
