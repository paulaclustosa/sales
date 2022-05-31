package com.letscode.sales.dto;

import lombok.Getter;

@Getter
public class CartRequest {
  private String productUuid;
  private int quantity;
}
