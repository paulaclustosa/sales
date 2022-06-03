package com.letscode.sales.model;

import com.letscode.sales.dto.ProductClientResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Product {
  private String uuid;
  private String name;
  private BigDecimal price;
  private int cartQuantity;

  public Product(ProductClientResponse productClientResponse) {
    this.uuid = productClientResponse.getUuid();
    this.name = productClientResponse.getName();
    this.price = productClientResponse.getPrice();
  }
}
