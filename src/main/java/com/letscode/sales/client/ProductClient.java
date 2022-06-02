package com.letscode.sales.client;

import com.letscode.sales.dto.ProductClientResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

  private WebClient webClient = WebClient.create("http://localhost:8081");

  public Mono<ProductClientResponse> findProductByUuid(String productUuid, int quantity) {

    return webClient.method(HttpMethod.GET)
        .uri("/products/{productUuid}/{quantity}", productUuid, quantity)
        .retrieve()
        .bodyToMono(ProductClientResponse.class);
  }

}
