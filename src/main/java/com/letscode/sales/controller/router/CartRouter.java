package com.letscode.sales.controller.router;

import com.letscode.sales.controller.handler.CartHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


//@AllArgsConstructor
//@RequiredArgsConstructor
@Configuration
public class CartRouter {

//  CartHandler handler;

  @Bean
  public RouterFunction<ServerResponse> routeShoppingCart(CartHandler handler) {
    return route()
        .path("/shopping-cart", b1 -> b1
          .nest(accept(APPLICATION_JSON), b2 -> b2
              .POST("", handler::createCart)
              .PATCH("/{cartUuid}", handler::updateCart)))
        .build();
  }

}
