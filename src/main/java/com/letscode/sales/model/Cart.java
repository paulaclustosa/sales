package com.letscode.sales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
  @Id
  private ObjectId id;
  private String uuid = UUID.randomUUID().toString();
  private List<Product> shoppingCart = new ArrayList<>();

  public Cart(Product product) {
    this.shoppingCart.add(product);
  }

  public void updateShoppingCart(Product product) {
    this.shoppingCart.add(product);
  }
}
