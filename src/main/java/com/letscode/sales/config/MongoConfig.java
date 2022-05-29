package com.letscode.sales.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public class MongoConfig extends AbstractReactiveMongoConfiguration {

  @Value("{mongo.uri}")
  String uriMongo;

  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create(uriMongo);
  }

  @Override
  protected String getDatabaseName() {
    return "sales";
  }
}
