package com.letscode.sales.service;

import com.letscode.sales.dto.*;
import com.letscode.sales.model.Cart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartService {

  public Mono<ProductClientResponse> createCart(CartRequest request);

  public Mono<ProductClientResponse> handleAddToCart(String cartUuid, CartRequest request);

  public Mono<CartResponse> updateCart(String cartUuid, CartRequest cartRequest);

  public Mono<CartResponse> removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest);

  public Mono<Void> deleteCart(String cartUuid);

  public Mono<Cart> findCartByUuid(String cartUuid);

  public Flux<FindCartResponse> findAll();
}
