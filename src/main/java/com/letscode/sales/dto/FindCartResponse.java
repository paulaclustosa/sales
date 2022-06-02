package com.letscode.sales.dto;

import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class FindCartResponse {
    private String uuid = UUID.randomUUID().toString();
    private Map<String, Product> products = new HashMap<>();
}
