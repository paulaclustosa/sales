package com.letscode.sales.dto;

import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Customer;
import com.letscode.sales.model.Sale;
import com.letscode.sales.model.Status;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaleResponse {
  private String uuid;
  private Customer customer;
  private Cart cart;
  private Status status;
  private LocalDateTime creationDate;

  public SaleResponse(Sale sale) {
    this.uuid = sale.getUuid();
    this.customer = sale.getCustomer();
    this.cart = sale.getCart();
    this.status = sale.getStatus();
    this.creationDate = sale.getCreationDate();
  }
}
