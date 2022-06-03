package com.letscode.sales.client;

import com.letscode.sales.config.WebClientConfig;
import com.letscode.sales.dto.ProductClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// @FeignClient(name = "product-api")
@Service
@RequiredArgsConstructor
public class ProductClient {
  //  @GetMapping("/{uuid}/{quantity}")
  //  Mono<ProductClientResponse> findProductByUuid(
  //      @PathVariable("uuid") String uuid, @PathVariable("quantity") int quantity);

//  private WebClient webClient = WebClient.create("http://localhost:8081");

  private final WebClientConfig webClient;
  public Mono<ProductClientResponse> findProductByUuid(String productUuid, int quantity) {
    return webClient.getWebClientBuilder().baseUrl("http://product-api").build()
        .method(HttpMethod.GET)
        .uri("/products/{productUuid}/{quantity}", productUuid, quantity)
        .retrieve()
        .bodyToMono(ProductClientResponse.class);
  }
}
