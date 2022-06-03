package com.letscode.sales.model;

import com.letscode.sales.dto.CustomerClientResponse;
import lombok.Getter;

@Getter
public class Customer {
  private String uuid;
  private String name;
  private String cpf;
  private String email;
  private String adress;

  public Customer(CustomerClientResponse customerClientResponse) {
    this.uuid = customerClientResponse.getUuid();
    this.name = customerClientResponse.getName();
    this.cpf = customerClientResponse.getCpf();
    this.email = customerClientResponse.getEmail();
    this.adress = customerClientResponse.getAdress();
  }
}
