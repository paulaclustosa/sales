package com.letscode.sales.service.impl;

import com.letscode.sales.model.Cart;
import com.letscode.sales.service.CartService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartServiceImpl implements CartService {
  @Override
  public Mono<Cart> createCart(String userUuid) {
    return null;
  }

  @Override
  public Mono<Cart> addToCart(String productUuid, int quantity) {
    return null;
  }

  @Override
  public Mono<Cart> removeFromCart(String productUuid, int quantity) {
    return null;
  }
}
