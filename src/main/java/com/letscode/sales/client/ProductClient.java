package com.letscode.sales.client;

import com.letscode.sales.config.WebClientConfig;
import com.letscode.sales.dto.ProductClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductClient {

  private final WebClientConfig webClient;

  public Mono<ProductClientResponse> findProductByUuid(String productUuid, int quantity) {
    return webClient.getWebClientBuilder().baseUrl("http://product-api").build()
        .method(HttpMethod.GET)
        .uri("/products/{productUuid}/{quantity}", productUuid, quantity)
        .retrieve()
        .bodyToMono(ProductClientResponse.class);
  }
}
