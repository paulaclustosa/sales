package com.letscode.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableEurekaClient
@SpringBootApplication
@EnableReactiveMongoAuditing
public class SalesApplication {
  public static void main(String[] args) {
    SpringApplication.run(SalesApplication.class, args);
  }
}
