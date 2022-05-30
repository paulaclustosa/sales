package com.letscode.sales.DTO;

import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
  private String uuid;
  private List<Product> shoppingCart;
  String message = "Product successfuly added to cart.";

  public CartResponse(Cart cart) {
    this.uuid = cart.getUuid();
    this.shoppingCart = cart.getShoppingCart();
  }
}
