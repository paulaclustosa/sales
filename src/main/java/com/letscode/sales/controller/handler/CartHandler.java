package com.letscode.sales.controller.handler;

import com.letscode.sales.dto.*;
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
        .body(
            BodyInserters.fromPublisher(
                cartMono.flatMap(cartService::createCart), ProductClientResponse.class));
  }

  public Mono<ServerResponse> findCartByUuid(ServerRequest request) {
    String cartUuid = request.pathVariable("cartUuid");

    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cartService.findCartByUuid(cartUuid), FindCartResponse.class);
  }

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cartService.findAll(), FindCartResponse.class);
  }

  public Mono<ServerResponse> handleAddToCart(ServerRequest request) {
    String cartId = request.pathVariable("cartUuid");
    Mono<CartRequest> cartMono = request.bodyToMono(CartRequest.class);
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            BodyInserters.fromPublisher(
                cartMono.flatMap(cartRequest -> cartService.handleAddToCart(cartId, cartRequest)),
                ProductClientResponse.class));
  }

  public Mono<ServerResponse> removeItemFromCart(ServerRequest request) {
    Mono<RemoveItemFromCartRequest> removeItemFromCartRequest = request.bodyToMono(RemoveItemFromCartRequest.class);
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            BodyInserters.fromPublisher(
                removeItemFromCartRequest.flatMap(cartService::removeItemFromCart),
                CartResponse.class));
  }

  public Mono<ServerResponse> deleteCart(ServerRequest request) {
    String cartUuid = request.pathVariable("cartUuid");
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(cartService.deleteCart(cartUuid), CartResponse.class);
  }
}
