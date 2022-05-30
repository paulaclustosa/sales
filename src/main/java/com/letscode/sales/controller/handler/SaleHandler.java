package com.letscode.sales.controller.handler;

import com.letscode.sales.DTO.CreateSaleRequest;
import com.letscode.sales.client.CustomerClient;
import com.letscode.sales.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
//@AllArgsConstructor
@RequiredArgsConstructor
public class SaleHandler {

  private final SaleService saleService;
  private final CustomerClient custumerClient;

  public Mono<ServerResponse> createSale(ServerRequest request) {
    Mono<CreateSaleRequest> saleMono = request.bodyToMono(CreateSaleRequest.class);
    return null;
  }
}
