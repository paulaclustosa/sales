package com.letscode.sales.service.impl;

import com.letscode.sales.client.CustomerClient;
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

    /*var a = customerClient.findCustomerByUuid(customerUuid)
      .doOnNext(customer -> {
        Sale createdSale = new Sale();
        createdSale.setCustomer(customer);
      }).flatMap(customer -> Mono.just(createdSale);

    }).*/

    //    Mono<Sale> saleMono =
    //        Mono.just(new Sale())
    //            .doOnNext(
    //                sale -> {
    //                  Customer customer =
    //                      customerClient
    //                          .findCustomerByUuid(customerUuid)
    //                          .doOnNext(sale::setCustomer)
    //                          .block();
    //
    // .then(cartRepository.findByUuid(cartUuid).doOnNext(sale::setCart));
    //                  customer
    //                      .doAfterTerminate(
    //                          (Runnable)
    // cartRepository.findByUuid(cartUuid).doOnNext(sale::setCart))
    //                      //                      .doAfterTerminate(
    //                      //                          (Runnable)
    //                      //
    // cartRepository.findByUuid(cartUuid).doOnNext(sale::setCart))
    //                      .subscribe();
    //
    //                  customer.subscribe();

    //                  Cart shoppingCart =
    //                      cartRepository.findByUuid(cartUuid).doOnNext(sale::setCart).block();

    //                  sale.setCart(shoppingCart);

    //                  saleRepository.save(sale).subscribe();
    //                });

    //    saleMono.subscribe();

    //    Mono<Sale> sale = Mono.just(new Sale());
    //        saleMono.subscribe();
    Mono<Customer> customer = customerClient.findCustomerByUuid(customerUuid);
    //      .doOnNext(sale::setCustomer);
    //        customer.subscribe();

    Mono<Cart> shoppingCart = cartRepository.findByUuid(cartUuid);
    //            .doOnNext(sale::setCart);
    //        shoppingCart.subscribe();

    //    Mono<Sale> savedSale = saleRepository.save(sale);
    //        savedSale.subscribe();

    return Mono.zip(customer, shoppingCart)
        .flatMap(
            monos -> {
              Customer customer1 = monos.getT1();
              Cart cart1 = monos.getT2();
              Sale sale = new Sale();
              sale.setCustomer(customer1);
              sale.setCart(cart1);
              return saleRepository.save(sale);
            })
        .map(SaleResponse::new);
  }
}
