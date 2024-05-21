package com.gmail.onlinegrocery.ecommerce.dto.Product;

import com.gmail.onlinegrocery.ecommerce.enums.SearchPerfume;
import lombok.Data;

@Data
public class SearchTypeRequest {
    private SearchPerfume searchType;
    private String text;
}
