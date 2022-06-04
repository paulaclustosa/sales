package com.letscode.sales.client;

import com.letscode.sales.config.WebClientConfig;
import com.letscode.sales.dto.CartListRequest;
import com.letscode.sales.dto.ProductClientResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductClient {

  private final WebClientConfig webClient;

  @CircuitBreaker(name = "product-cb")
  public Mono<ProductClientResponse> findProductByUuid(String productUuid, int quantity) {
    return webClient.getWebClientBuilder().baseUrl("http://product-api").build()
        .method(HttpMethod.GET)
        .uri("/products/{productUuid}/{quantity}", productUuid, quantity)
        .retrieve()
        .bodyToMono(ProductClientResponse.class);
  }

  @CircuitBreaker(name = "product-cb")
  public Mono<String> removeItemsfromInventory(CartListRequest cartRequest) {
    log.info("Entrei no client do Product para atualizar o inventário");


    return webClient.getWebClientBuilder().baseUrl("http://product-api").build()
        .method(HttpMethod.PUT)
        .uri("/products/remove-items")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(cartRequest), CartListRequest.class)
        .retrieve()
        .bodyToMono(String.class);
        //.bodyToMono();
  }
}
