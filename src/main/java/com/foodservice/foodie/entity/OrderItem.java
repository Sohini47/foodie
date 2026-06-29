package com.foodservice.foodie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name ="orderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name ="menuItemId", nullable = false)
    private MenuItem menuItem;
}
