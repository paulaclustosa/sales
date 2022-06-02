package com.letscode.sales.service;

import com.letscode.sales.dto.CartRequest;
import com.letscode.sales.dto.CartResponse;
import com.letscode.sales.dto.FindCartResponse;
import com.letscode.sales.dto.RemoveItemFromCartRequest;
import com.letscode.sales.model.Cart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartService {

  public Mono<CartResponse> createCart(CartRequest request);

  public Mono<CartResponse> handleAddToCart(String cartUuid, CartRequest request);

  public Mono<CartResponse> updateCart(String cartUuid, String productUuid, int quantity);

  public Mono<CartResponse> removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest);

  public Mono<Void> deleteCart(String cartUuid);

  public Mono<Cart> findCartByUuid(String cartUuid);

  public Flux<FindCartResponse> findAll();
}
