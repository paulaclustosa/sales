package com.letscode.sales.service.impl;

import com.letscode.sales.client.CustomerClient;
import com.letscode.sales.client.ProductClient;
import com.letscode.sales.dto.*;
import com.letscode.sales.model.Cart;
import com.letscode.sales.model.Customer;
import com.letscode.sales.model.Sale;
import com.letscode.sales.model.Status;
import com.letscode.sales.repository.CartRepository;
import com.letscode.sales.repository.SaleRepository;
import com.letscode.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;
  private final CartRepository cartRepository;
  private final CustomerClient customerClient;
  private final ProductClient productClient;

  @Override
  public Mono<SaleResponse> createSale(CreateSaleRequest createSaleRequest) {
    String customerUuid = createSaleRequest.getCustomerUuid();
    String cartUuid = createSaleRequest.getCartUuid();

    Mono<CustomerClientResponse> customerClientResponse =
        customerClient.findUserByUuid(customerUuid);

    Mono<Cart> shoppingCart = cartRepository.findByUuid(cartUuid);

    return Mono.zip(customerClientResponse, shoppingCart)
        .flatMap(
            monos -> {
              CustomerClientResponse customerResponse = monos.getT1();
              Customer customer = new Customer(customerResponse);
              Cart cart1 = monos.getT2();

              List<CartRequest> productsOfCart = cartToCartRequestDto(cart1);
              productClient.removeItemsfromInventory(new CartListRequest(productsOfCart)).subscribe();
              Sale sale = new Sale();
              sale.setCustomer(customer);
              cart1.setStatus(Status.FINISHED);
              cartRepository.save(cart1).subscribe();
              sale.setCart(cart1);

//              removeItemsFromProduct(cart1);
              return saleRepository.save(sale);
            })
        .map(SaleResponse::new);
  }

//  private void removeItemsFromProduct(Cart cart) {
//    cart.getProducts().forEach((key, product) -> {
//      productClient.removeItemsfromInventory(key, product.getCartQuantity()).subscribe();
//    });
//  }

  private List<CartRequest> cartToCartRequestDto(Cart cart) {
    List<CartRequest> cartRequestList = new ArrayList<>();
    cart.getProducts().forEach((key, product) -> {

      CartRequest cartRequest = new CartRequest(key, product.getCartQuantity());
      cartRequestList.add(cartRequest);
    });
    return cartRequestList;
  }

}
