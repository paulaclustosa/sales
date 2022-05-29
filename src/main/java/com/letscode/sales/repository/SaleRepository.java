package com.letscode.sales.repository;

import com.letscode.sales.model.Sale;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends ReactiveCrudRepository<Sale, ObjectId> {}
