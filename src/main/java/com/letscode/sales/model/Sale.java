package com.letscode.sales.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
public class Sale {

  @Id private ObjectId id;

  private String uuid = UUID.randomUUID().toString();

  private Customer customer;

  @Field(name = "shopping-cart")
  private Cart cart;

  @CreatedDate private Date creationDate;

}
