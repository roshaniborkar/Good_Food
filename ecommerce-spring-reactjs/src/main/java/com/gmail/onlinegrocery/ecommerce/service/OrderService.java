package com.gmail.onlinegrocery.ecommerce.service;

import com.gmail.onlinegrocery.ecommerce.domain.Order;
import com.gmail.onlinegrocery.ecommerce.domain.OrderItem;
import graphql.schema.DataFetcher;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order getOrderById(Long orderId);

    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    
    Page<Order> getAllOrders(Pageable pageable);

    List<Order> getUserOrders(String email);

    Order postOrder(Order validOrder, Map<Long, Long> productId);

    String deleteOrder(Long orderId);

    DataFetcher<List<Order>> getAllOrdersByQuery();

    DataFetcher<List<Order>> getUserOrdersByEmailQuery();
}
