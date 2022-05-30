package com.letscode.sales.service.impl;

import com.letscode.sales.dto.CartRequest;
import com.letscode.sales.dto.CartResponse;
import com.letscode.sales.dto.ProductClientResponse;
import com.letscode.sales.client.ProductClient;
import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Product;
import com.letscode.sales.repository.CartRepository;
import com.letscode.sales.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

  private final CartRepository cartRespository;
  private final ProductClient productClient;

  @Override
  public Mono<CartResponse> createCart(CartRequest cartRequest) {
    Mono<ProductClientResponse> productClientResponseMono = productClient
        .findProductByUuid(cartRequest.getProductUuid())
        .doOnSuccess(
            productClientResponse -> {
              Product productToBeAdded = new Product(productClientResponse);
              productToBeAdded.setCartQuantity(cartRequest.getQuantity());
              Cart createdCart = new Cart(productToBeAdded);

              Mono<Cart> savedCart = cartRespository
                  .save(createdCart)
                  .doOnSuccess(
                      cart -> {
                        log.info(cart.getUuid());
                        log.info(cart.getShoppingCart().get(0).getName());
                      });
              savedCart.subscribe(s -> log.info("Value {}", s.getUuid()));
            });
    productClientResponseMono.subscribe(s -> log.info("Value {}", s.getName()));

    return Mono.just(new CartResponse());
  }

  @Override
  public Mono<CartResponse> addToCart(String cartUuid, CartRequest cartRequest) {
    log.info("Entered addToCart function...");

    Mono<ProductClientResponse> productClientResponseMono =
        productClient.findProductByUuid(cartRequest.getProductUuid());

    Mono<ProductClientResponse> productMono = productClientResponseMono.doOnSuccess(productClientResponse -> {
      Product product = new Product(productClientResponse);
      product.setCartQuantity(cartRequest.getQuantity());

      Mono<Cart> cartMono = cartRespository.findByUuid(cartUuid).doOnSuccess(cart -> {
        cart.updateShoppingCart(product);
        Mono<Cart> savedCart = cartRespository.save(cart);
        savedCart.subscribe();
      });

      cartMono.subscribe(s -> log.info("Value log1 {}", s.getShoppingCart().toString()));
    });

    productMono.subscribe(s -> log.info("Value log2 {}", s.getName()));
    return Mono.just(new CartResponse());
  }

  @Override
  public Mono<CartResponse> removeFromCart(CartRequest request) {
    return null;
  }
}
