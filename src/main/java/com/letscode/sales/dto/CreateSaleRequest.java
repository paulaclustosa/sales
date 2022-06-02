package com.letscode.sales.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateSaleRequest {

  @NotNull
  String customerUuid;

  @NotNull
  String cartUuid;
}
