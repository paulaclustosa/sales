package com.letscode.sales.controller.handler;

import com.letscode.sales.dto.CreateSaleRequest;
import com.letscode.sales.dto.SaleResponse;
import com.letscode.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaleHandler {

  private final SaleService saleService;

  public Mono<ServerResponse> createSale(ServerRequest request) {

    Mono<CreateSaleRequest> saleMono = request.bodyToMono(CreateSaleRequest.class);

    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(saleMono.flatMap(saleService::createSale), SaleResponse.class));
  }
}

//saleService.createSale(
//    saleRequest.getCustomerUuid(), saleRequest.getCartUuid())),
