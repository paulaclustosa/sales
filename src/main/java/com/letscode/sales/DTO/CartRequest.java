package com.letscode.sales.DTO;

import lombok.Getter;

@Getter
public class CartRequest {
  private String productUuid;
  private int quantity;
}
