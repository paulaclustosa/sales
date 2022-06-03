package com.letscode.sales.client;

import com.letscode.sales.config.WebClientConfig;
import com.letscode.sales.dto.CustomerClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// @FeignClient(name = "user-api", url = "localhost:8080")
// public interface CustomerClient {
@Service
@RequiredArgsConstructor
public class CustomerClient {

  //  @GetMapping("/{uuid}")
  //  Mono<CustomerClientResponse> findUserByUuid(@PathVariable String uuid);

//  private WebClient webClient = WebClient.create("http://user-api");

  private final WebClientConfig webClient;

  public Mono<CustomerClientResponse> findUserByUuid(String customerUuid) {
    //    try {
    //      Thread.sleep(2000);
    //    } catch (InterruptedException e) {
    //      throw new RuntimeException(e);
    //    }
    return webClient.getWebClientBuilder().baseUrl("http://user-api").build()
        .method(HttpMethod.GET)
        .uri("/users/{customerUuid}", customerUuid)
        .retrieve()
        .bodyToMono(CustomerClientResponse.class);
  }
}
