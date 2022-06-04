package com.letscode.sales.service.impl;

import com.letscode.sales.client.ProductClient;
import com.letscode.sales.dto.*;
import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Product;
import com.letscode.sales.model.Status;
import com.letscode.sales.repository.CartRepository;
import com.letscode.sales.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final ProductClient productClient;

  // Situação: carrinho ainda não criado. Aqui se,
  // cria o carrinho e adiciona o produto e sua quantidade ao carrinho criado
  @Override
  public Mono<ProductClientResponse> createCart(CartRequest cartRequest) {
    String productUuid = cartRequest.getProductUuid();
    int productQuantity = cartRequest.getQuantity();

    Mono<ProductClientResponse> productClientResponseMono =
        productClient
            .findProductByUuid(productUuid, productQuantity)
            .doOnNext(
                productClientResponse -> {
                  Product newProduct = new Product(productClientResponse);
                  newProduct.setCartQuantity(cartRequest.getQuantity());

                  Cart newCart = new Cart(newProduct);
                  newCart.updateSubtotal();
                  Mono<Cart> savedCart =
                      cartRepository.save(newCart).doOnNext(cart -> log.info(cart.getUuid()));
                  savedCart.subscribe(s -> log.info("Value {}", s.getUuid()));
                });

    productClientResponseMono.subscribe(s -> log.info("Value {}", s.getName()));

    return productClientResponseMono;
  }

  @Override
  public Mono<Cart> findCartByUuid(String cartUuid) {
    return this.cartRepository.findByUuid(cartUuid);
  }

  @Override
  public Flux<FindCartResponse> findAll() {
    return this.cartRepository.findAll().map(MapperToFindCartResponse::execute);
  }

  // Situação: carrinho já criado. Aqui se
  // adiciona o novo produto e sua respectiva quantidade ao carrinho
  // ou, caso o produto já esteja no carrinho, atualiza a sua quantidade (quantidade que se tinha +
  // quantidade que se deseja aumentar)
  @Override
  public Mono<ProductClientResponse> handleAddToCart(String cartUuid, CartRequest cartRequest) {
    log.info("Entered addToCart function...");

    Mono<ProductClientResponse> productClientResponseMono =
        productClient.findProductByUuid(cartRequest.getProductUuid(), cartRequest.getQuantity());

    Mono<ProductClientResponse> productMono =
        productClientResponseMono.doOnNext(
            productClientResponse -> {
              Product product = new Product(productClientResponse);

              Mono<Cart> cartMono =
                  cartRepository
                      .findByUuid(cartUuid)
                      .doOnNext(
                          cart -> {
                            //if (cart.getStatus() == Status.FINISHED) { throw new Ex }
                              addToCart(cart, product, cartRequest.getQuantity());
                            cart.updateSubtotal();
                            Mono<Cart> savedCart = cartRepository.save(cart);
                            savedCart.subscribe();
                          });

              cartMono.subscribe(s -> log.info("Value log1 {}", s.getProducts().toString()));
            });

    productMono.subscribe(s -> log.info("Value log2 {}", s.getName()));
    return productMono;
  }

  private void addToCart(
      Cart cartRetrievedFromDb, Product productToAddToCart, int quantityFromRequest) {
    String productUuid = productToAddToCart.getUuid();
    Map<String, Product> cartProducts = cartRetrievedFromDb.getProducts();

    if (cartProducts.containsKey(productUuid)) {
      int previousQuantity = cartProducts.get(productUuid).getCartQuantity();
      int updatedQuantity = previousQuantity + quantityFromRequest;

      productToAddToCart.setCartQuantity(updatedQuantity);
      cartProducts.put(productUuid, productToAddToCart);
    } else {
      productToAddToCart.setCartQuantity(quantityFromRequest);
      cartProducts.put(productUuid, productToAddToCart);
    }
  }

  // Situação: carrinho já criado. Aqui se
  // adiciona o novo produto e sua respectiva quantidade ao carrinho
  // sobrescrevendo o anterior
  @Override
  public Mono<CartResponse> updateCart(String cartUuid, CartRequest cartRequest) {
    // Recuperar Carrinho
    Mono<ProductClientResponse> productClientResponseMono =
        productClient.findProductByUuid(cartRequest.getProductUuid(), cartRequest.getQuantity());

    Mono<ProductClientResponse> productMono =
        productClientResponseMono.doOnNext(
            productClientResponse -> {
              Product productToBeAdded = new Product(productClientResponse);
              productToBeAdded.setCartQuantity(cartRequest.getQuantity());

              Mono<Cart> cartMono =
                  cartRepository
                      .findByUuid(cartUuid)
                      .doOnNext(
                          cart -> {
                            Map<String, Product> products = cart.getProducts();
                            products.put(productToBeAdded.getUuid(), productToBeAdded);
                            cartRepository.save(cart).subscribe();
                          });

              cartMono.subscribe();
            });
    productMono.subscribe();
    return Mono.just(new CartResponse());
  }

  @Override
  public Mono<CartResponse> removeItemFromCart(
      RemoveItemFromCartRequest removeItemFromCartRequest) {

    CartResponse cartUpdatedResponse = new CartResponse();

    Mono<CartResponse> cartMono =
        cartRepository
            .findByUuid(removeItemFromCartRequest.getCartUuid())
            .doOnNext(
                cart -> {
                  cart.getProducts().remove(removeItemFromCartRequest.getProductUuid());
                  Mono<Cart> savedCart = cartRepository.save(cart);
                  savedCart.subscribe();
                  cartUpdatedResponse.setProducts(cart.getProducts());
                }).map(CartResponse::new);
    cartMono.subscribe();

    return cartMono;
  }

  @Override
  public Mono<Void> deleteCart(String cartUuid) {
    return cartRepository.deleteByUuid(cartUuid);
  }
}
