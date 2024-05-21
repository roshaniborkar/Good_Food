package com.gmail.onlinegrocery.ecommerce.dto.Product;

import lombok.Data;

import java.util.List;

@Data
public class PerfumeSearchRequest {
    private List<String> perfumers;
    private List<String> genders;
    private List<Integer> prices;
    private Boolean sortByPrice;
    private String perfumer;
    private String perfumeGender;
}
