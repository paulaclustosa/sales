package com.letscode.sales.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CartRequest {
  @NotNull private String productUuid;
  @NotNull private int quantity;

  public CartRequest(String key, int cartQuantity) {
    this.productUuid = key;
    this.quantity = cartQuantity;
  }
}
