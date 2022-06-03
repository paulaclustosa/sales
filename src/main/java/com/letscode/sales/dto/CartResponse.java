package com.letscode.sales.dto;

import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
  //  String message = "Product successfully added to cart.";
  Map<String, Product> products = new HashMap<>();

  public CartResponse(Cart cart) {
    this.products = cart.getProducts();
  }

}
