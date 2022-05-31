package com.letscode.sales.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductClientResponse {
  private String uuid;
  private String name;
  private BigDecimal price;
  private int quantity;
}
