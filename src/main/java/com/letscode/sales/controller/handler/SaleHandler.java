package com.letscode.sales.controller.handler;

import com.letscode.sales.dto.CreateSaleRequest;
import com.letscode.sales.client.CustomerClient;
import com.letscode.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaleHandler {

  private final SaleService saleService;
  private final CustomerClient custumerClient;

  public Mono<ServerResponse> createSale(ServerRequest request) {
    Mono<CreateSaleRequest> saleMono = request.bodyToMono(CreateSaleRequest.class);
    return null;
  }
}
