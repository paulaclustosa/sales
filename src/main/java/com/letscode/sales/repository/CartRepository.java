package com.letscode.sales.repository;

import com.letscode.sales.model.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, ObjectId> {
  Mono<Cart> findByUuid(String uuid);
  Mono<Void> deleteByUuid(String Uuid);
  //Mono<Void> deleteByUuid(Cart cart);
}
