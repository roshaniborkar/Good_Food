package com.gmail.onlinegrocery.ecommerce.mapper;

import com.gmail.onlinegrocery.ecommerce.domain.Order;
import com.gmail.onlinegrocery.ecommerce.domain.OrderItem;
import com.gmail.onlinegrocery.ecommerce.domain.Product;
import com.gmail.onlinegrocery.ecommerce.dto.HeaderResponse;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderItemResponse;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderRequest;
import com.gmail.onlinegrocery.ecommerce.dto.order.OrderResponse;
import com.gmail.onlinegrocery.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CommonMapper commonMapper;
    private final OrderService orderService;
    
    public OrderResponse getOrderById(Long orderId) {
        return commonMapper.convertToResponse(orderService.getOrderById(orderId), OrderResponse.class);
    }
    
    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        List<OrderItemResponse> result= new ArrayList<OrderItemResponse>();
        for(OrderItem order:  orderService.getOrderItemsByOrderId(orderId))
        {
            OrderItemResponse orderResponse =  commonMapper.convertToResponse(order,OrderItemResponse.class);
            orderResponse.setProduct(mapToProductDto(order.getProduct()));
            result.add(orderResponse);
        }
        return result;
    }

    public HeaderResponse<OrderResponse> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderService.getAllOrders(pageable);
        return commonMapper.getHeaderResponse(orders.getContent(), orders.getTotalPages(), orders.getTotalElements(), OrderResponse.class);
    }

    public List<OrderResponse> getUserOrders(String email) {
        List<Order> orders = orderService.getUserOrders(email);
        List<OrderResponse> result = new ArrayList<>();
        for(Order order : orders){
            result.add(mapOrderToOrderResponse(order));
        }
        return result;
    }

    public String deleteOrder(Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    public OrderResponse postOrder(OrderRequest orderRequest) {

        Order order = orderService.postOrder(commonMapper.convertToEntity(orderRequest, Order.class), orderRequest.getProductId());
        return commonMapper.convertToResponse(order, OrderResponse.class);
    }

    private OrderResponse mapOrderToOrderResponse(Order order){
        OrderResponse orderDto = new OrderResponse();
        orderDto.setId(order.getId());
        orderDto.setCity(order.getCity());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setAddress(order.getAddress());
        orderDto.setDate(order.getDate());
        orderDto.setEmail(order.getEmail());
        orderDto.setFirstName(order.getFirstName());
        orderDto.setLastName(order.getLastName());
        orderDto.setPhoneNumber(order.getPhoneNumber());
        orderDto.setPostIndex(order.getPostIndex());

        return  orderDto;
    }

    private ProductDTO mapToProductDto(Product product) {
        ProductDTO result = new ProductDTO();
        result.setProductId(product.getId());
        result.setProductName(product.getProductName());
        result.setImagePath(product.getImagePath());
        result.setProductTag(product.getProductTag());
        result.setPrice(product.getPrice());
        result.setProductCategory(product.getCategory().getCategoryName());
        result.setSeller(product.getSeller());
        result.setDescription(product.getDescription());
        result.setRating(product.getRating());
        return result;
    }
}
