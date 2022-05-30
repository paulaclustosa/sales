package com.letscode.sales.controller.handler;

import com.letscode.sales.DTO.CartRequest;
import com.letscode.sales.DTO.CartResponse;
import com.letscode.sales.service.CartService;
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
public class CartHandler {

  private final CartService cartService;

  public Mono<ServerResponse> createCart(ServerRequest request) {
    Mono<CartRequest> cartMono = request.bodyToMono(CartRequest.class);

    return ServerResponse.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(cartMono.flatMap(cartService::createCart), CartResponse.class));
  }

  public Mono<ServerResponse> updateCart(ServerRequest request) {
    String cartId = request.pathVariable("cartUuid");
    Mono<CartRequest> cartMono = request.bodyToMono(CartRequest.class);
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(cartMono.flatMap(cartRequest ->
          cartService.addToCart(cartId, cartRequest)), CartResponse.class));
  }
}
