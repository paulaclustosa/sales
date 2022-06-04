package com.letscode.sales.handler;

import com.letscode.sales.dto.errors.BusinessValidationErrorResponse;
import com.letscode.sales.dto.errors.FieldValidationErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handler(MethodArgumentNotValidException exception) {
    List<FieldValidationErrorResponse> fieldValidationErrorResponseList = new ArrayList<>();
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    fieldErrors.forEach(e -> {
      String errorMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
      FieldValidationErrorResponse fieldValidationErrorResponse =
          FieldValidationErrorResponse
              .builder()
              .field(e.getField())
              .errorMessage(errorMessage)
              .build();
      fieldValidationErrorResponseList.add(fieldValidationErrorResponse);
    });

    return new ResponseEntity(fieldValidationErrorResponseList, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity handleException(RuntimeException e) {
    BusinessValidationErrorResponse error =
        BusinessValidationErrorResponse
            .builder()
            .errorMessage(e.getMessage())
            .code(HttpStatus.FORBIDDEN.value())
            .ocurrenceDate(LocalDateTime.now())
            .build();
    return new ResponseEntity(error, HttpStatus.FORBIDDEN);
  }

}
