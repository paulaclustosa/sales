package com.letscode.sales.service;

import com.letscode.sales.model.Sale;
import reactor.core.publisher.Mono;

public interface SaleService{

  public Mono<Void> cancelSale(String saleUuid);

  public Mono<Sale> finishSale(String saleUuid);
}
