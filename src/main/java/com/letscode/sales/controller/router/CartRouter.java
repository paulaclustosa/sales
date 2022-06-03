package com.letscode.sales.controller.router;

import com.letscode.sales.controller.handler.CartHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CartRouter {

  @Bean
  public RouterFunction<ServerResponse> routeShoppingCart(CartHandler handler) {
    return route()
        .path(
            "/shopping-cart", b1 -> b1
              .nest(accept(APPLICATION_JSON), b2 -> b2
                  .POST("", handler::createCart)
                  .GET("/{cartUuid}", handler::findCartByUuid)
                  .GET("", handler::findAll)
                  .PATCH("/{cartUuid}", handler::handleAddToCart)
                  .PATCH("", handler::removeItemFromCart)
                  .DELETE("/{cartUuid}", handler::deleteCart)))
        .build();
  }
}
