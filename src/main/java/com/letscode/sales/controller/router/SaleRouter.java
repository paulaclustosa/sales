package com.letscode.sales.controller.router;

import com.letscode.sales.controller.handler.SaleHandler;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SaleRouter {
  
  @Bean
  @RouterOperations
  public RouterFunction<ServerResponse> router(SaleHandler handler){

    return route()
        .path("/sales", b1 -> b1
          .nest(accept(APPLICATION_JSON), b2 -> b2
              .POST("", handler::createSale)))
        .build();
  }
}
