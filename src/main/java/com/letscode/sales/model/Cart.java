package com.letscode.sales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
  @Id private ObjectId id;
  private String uuid = UUID.randomUUID().toString();
  private Map<String, Product> products = new HashMap<>();
  private BigDecimal subtotal = new BigDecimal(0);

  public Cart(Product product) {
    this.products.put(product.getUuid(), product);
    updateSubtotal();
  }

  public void updateSubtotal() {
    BigDecimal subtotalInit = new BigDecimal(0);

    for (Product product : this.products.values()) {
      BigDecimal cartQuantity = BigDecimal.valueOf(product.getCartQuantity());
      BigDecimal sum = product.getPrice().multiply(cartQuantity);
      subtotalInit.add(sum);
    }
    this.subtotal = subtotalInit;
  }
}
