package com.letscode.sales.client;

import com.letscode.sales.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

  private WebClient webClient = WebClient.create("http://localhost:8081");

  public Mono<Product> findProductById(String productId) {
    return webClient.get()
        .uri("/products/{uuid}")
        .retrieve()
        .bodyToMono(Product.class);
  }

}
