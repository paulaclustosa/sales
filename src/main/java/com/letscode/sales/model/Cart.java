package com.letscode.sales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
  @Id
  private ObjectId id;
  private String uuid = UUID.randomUUID().toString();
  //private List<Product> shoppingCart = new ArrayList<>();
  private Map<String, Product> products = new HashMap<>();

  public Cart(Product product) {
    //this.shoppingCart.add(product);
    this.products.put(product.getUuid(), product);
  }

  public void updateShoppingCart(Product createdProduct, int quantityFromRequest) {
    String productUuid = createdProduct.getUuid();

    if (products.containsKey(productUuid)) {
      int previousQuantity = products.get(productUuid).getCartQuantity();
      int updatedQuantity = previousQuantity + quantityFromRequest;
      createdProduct.setCartQuantity(updatedQuantity);
      this.products.put(productUuid, createdProduct);
    }
    else {
      createdProduct.setCartQuantity(quantityFromRequest);
      this.products.put(productUuid, createdProduct);
    }

    //this.shoppingCart.add(product);
  }
}
