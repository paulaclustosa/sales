package com.letscode.sales.service.impl;

import com.letscode.sales.client.CustomerClient;
import com.letscode.sales.dto.CustomerClientResponse;
import com.letscode.sales.dto.SaleResponse;
import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Customer;
import com.letscode.sales.model.Sale;
import com.letscode.sales.repository.CartRepository;
import com.letscode.sales.repository.SaleRepository;
import com.letscode.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;
  private final CartRepository cartRepository;
  private final CustomerClient customerClient;

  @Override
  public Mono<SaleResponse> createSale(String customerUuid, String cartUuid) {

    Mono<CustomerClientResponse> customerClientResponse =
        customerClient.findUserByUuid(customerUuid);

    Mono<Cart> shoppingCart = cartRepository.findByUuid(cartUuid);

    return Mono.zip(customerClientResponse, shoppingCart)
        .flatMap(
            monos -> {
              CustomerClientResponse customer1 = monos.getT1();
              Customer customer = new Customer(customer1);
              Cart cart1 = monos.getT2();
              Sale sale = new Sale();
              sale.setCustomer(customer);
              sale.setCart(cart1);
              return saleRepository.save(sale);
            })
        .map(SaleResponse::new);
  }
}
