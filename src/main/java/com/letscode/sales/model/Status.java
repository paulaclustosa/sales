package com.letscode.sales.model;

import lombok.Getter;

@Getter
public enum Status {
  OPEN("OPEN"),
  CANCELED("CANCELED"),
  FINISHED("FINISHED");

  private final String status;

  Status(String status){
    this.status = status;
  }
}
