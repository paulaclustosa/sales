package com.letscode.sales.client;

import com.letscode.sales.model.Customer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerClient {

  private WebClient webClient = WebClient.create("http://localhost:8080");

  public Mono<Customer> findCustomerByUuid(String customerUuid) {
    return webClient.method(HttpMethod.GET)
        .uri("/users/{customerUuid}", customerUuid)
        .retrieve()
        .bodyToMono(Customer.class);
  }

}
