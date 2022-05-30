package com.letscode.sales.service;

import com.letscode.sales.dto.CartRequest;
import com.letscode.sales.dto.CartResponse;
import reactor.core.publisher.Mono;

public interface CartService {

  public Mono<CartResponse> createCart(CartRequest request);

  public Mono<CartResponse> addToCart(String cartUuid, CartRequest request);

  public Mono<CartResponse> removeFromCart(CartRequest request);
}
