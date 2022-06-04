package com.letscode.sales.client;

import com.letscode.sales.config.WebClientConfig;
import com.letscode.sales.dto.CustomerClientResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerClient {

  private final WebClientConfig webClient;

  @CircuitBreaker(name = "costumer-cb")
  public Mono<CustomerClientResponse> findUserByUuid(String customerUuid) {
    return webClient.getWebClientBuilder().baseUrl("http://user-api").build()
        .method(HttpMethod.GET)
        .uri("/users/{customerUuid}", customerUuid)
        .retrieve()
        .bodyToMono(CustomerClientResponse.class);
  }
}
