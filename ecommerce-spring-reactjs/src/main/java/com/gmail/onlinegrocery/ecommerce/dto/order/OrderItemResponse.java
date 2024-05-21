package com.gmail.onlinegrocery.ecommerce.dto.order;

import com.gmail.onlinegrocery.ecommerce.dto.Product.PerfumeResponse;
import com.gmail.onlinegrocery.ecommerce.dto.Product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private ProductDTO product;
}
