package com.letscode.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartListRequest {
  List<CartRequest> productsOfCart;
}
