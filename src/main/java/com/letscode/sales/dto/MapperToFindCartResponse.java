package com.letscode.sales.dto;

import com.letscode.sales.model.Cart;

public class MapperToFindCartResponse {

    public static FindCartResponse execute(Cart cart) {
        return FindCartResponse.builder()
                .uuid(cart.getUuid())
                .products(cart.getProducts())
                .build();
    }
}
