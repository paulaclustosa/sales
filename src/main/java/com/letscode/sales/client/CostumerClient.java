package com.letscode.sales.client;

import com.letscode.sales.model.Costumer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CostumerClient {

  private WebClient webClient = WebClient.create("http://localhost:8080");

  public Mono<Costumer> findCostumerById(String customerId) {
    return webClient.get()
        .uri("/users/{custumerId}")
        .retrieve()
        .bodyToMono(Costumer.class);
  }

}
