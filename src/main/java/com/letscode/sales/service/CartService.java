package com.letscode.sales.service;

import com.letscode.sales.model.Cart;
import reactor.core.publisher.Mono;

public interface CartService {
  public Mono<Cart> createCart(String userUuid);

  public Mono<Cart> addToCart(String productUuid, int quantity);

  public Mono<Cart> removeFromCart(String productUuid, int quantity);
}
