package com.letscode.sales.model;

import lombok.Getter;

@Getter
public enum Status {
  OPEN("open"),
  CANCELED("canceled"),
  FINISHED("finished");

  private final String status;

  Status(String status){
    this.status = status;
  }
}
