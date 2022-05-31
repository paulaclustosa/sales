package com.letscode.sales.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
  String message = "Product successfully added to cart.";
}
