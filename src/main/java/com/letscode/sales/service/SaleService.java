package com.letscode.sales.service;

import com.letscode.sales.dto.SaleResponse;
import com.letscode.sales.model.Sale;
import reactor.core.publisher.Mono;

public interface SaleService {

  Mono<SaleResponse> createSale(String costumerUuid, String cartUuid);
}
