package com.gmail.onlinegrocery.ecommerce.controller;

import com.gmail.onlinegrocery.ecommerce.dto.GraphQLRequest;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderItemResponse;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderRequest;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderResponse;
import com.gmail.onlinegrocery.ecommerce.mapper.OrderMapper;
import com.gmail.onlinegrocery.ecommerce.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.onlinegrocery.ecommerce.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_ORDER)
public class OrderController {

    private final OrderMapper orderMapper;
    private final GraphQLProvider graphQLProvider;

    @GetMapping(ORDER_ID)
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderById(orderId));
    }

    @GetMapping(ORDER_ID_ITEMS)
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderItemsByOrderId(orderId));
    }

    @GetMapping(OrdersByEmail)
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable String email) {
        List<OrderResponse> result= orderMapper.getUserOrders(email);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> postOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok(orderMapper.postOrder(order));
    }

    @PostMapping(GRAPHQL)
    public ResponseEntity<ExecutionResult> getUserOrdersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
