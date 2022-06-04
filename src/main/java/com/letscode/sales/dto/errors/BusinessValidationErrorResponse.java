package com.letscode.sales.dto.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BusinessValidationErrorResponse {
  private int code;
  private LocalDateTime ocurrenceDate;
  private String errorMessage;
}
