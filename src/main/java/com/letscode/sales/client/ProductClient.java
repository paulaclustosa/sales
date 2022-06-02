package com.letscode.sales.client;

import com.letscode.sales.dto.ProductClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-api", url = "localhost:8081")
public interface ProductClient {
  @GetMapping("/{uuid}/{quantity}")
  ProductClientResponse getProductForSale(
      @PathVariable("uuid") String uuid, @PathVariable("quantity") int quantity);

  //
  //  private WebClient webClient = WebClient.create("http://localhost:8081");
  //
  //  public Mono<ProductClientResponse> findProductByUuid(String productUuid, int quantity) {
  //
  //    return webClient.method(HttpMethod.GET)
  //        .uri("/products/{productUuid}/{quantity}", productUuid, quantity)
  //        .retrieve()
  //        .bodyToMono(ProductClientResponse.class);
  //  }

}
