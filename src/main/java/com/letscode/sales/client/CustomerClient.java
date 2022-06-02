package com.letscode.sales.client;

import com.letscode.sales.dto.CustomerClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-api", url = "localhost:8080")
public interface CustomerClient {

  @GetMapping("/{uuid}")
  CustomerClientResponse findUserByUuid(@PathVariable String uuid);

  //  private WebClient webClient = WebClient.create("http://localhost:8080");
  //
  //  public Mono<Customer> findCustomerByUuid(String customerUuid) {
  //    try {
  //      Thread.sleep(2000);
  //    } catch (InterruptedException e) {
  //      throw new RuntimeException(e);
  //    }
  //    return webClient
  //        .method(HttpMethod.GET)
  //        .uri("/users/{customerUuid}", customerUuid)
  //        .retrieve()
  //        .bodyToMono(Customer.class);
  //  }
}
