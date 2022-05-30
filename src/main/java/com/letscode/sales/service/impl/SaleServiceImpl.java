package com.letscode.sales.service.impl;

import com.letscode.sales.model.Sale;
import com.letscode.sales.repository.SaleRepository;
import com.letscode.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;

  @Override
  public Mono<Sale> createSale(String costumerUuid, String productUuid) {
    return null;
  }

  @Override
  public Mono<Void> cancelSale(String saleUuid) {
    return null;
  }

  @Override
  public Mono<Sale> finishSale(String saleUuid) {
    return null;
  }
}
