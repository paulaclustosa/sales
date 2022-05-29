package com.letscode.sales.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Cart {
  private Costumer costumer;
  private List<Product> shoppingCart;
}
