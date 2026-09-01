#Grocery Store Backend Using Spring Boot Microservices

#Description
A microservices-based grocery store application built using Spring Boot, Docker, and API Gateway.  
It manages products, orders, and inventory with centralized service communication.

#Features
- Product and Categories Management Service
- Inventory Validation
- Order Creation and tracking
- Api Gateway routing
- Docker deployment

#Tech Stack
- Java / Spring Boot
- Spring Cloud Gateway 
-  PostgreSQL
- Docker & Docker compose 
-  Maven

#Project Structure

Grocery-Store
 |- Docker Compose/
 |- eurekaserver/
 |- gatewayserver/
 |- inventory-service/
 |- order-service/
 |- product-sevice/


#How to run the project

##Using Docker

terminal & bash
docker compose up -d

##Without Docker 

Update the configuration :

1.Start PostgreSQl is running and databases are created

2.Update the configuration :
(applicaion.yml)
spring:
 datasource:
  url: "jdbc:postgresql://localhost:5432/product_db"
  username: postgres
  password: admin

eureka:
  client:
     service-url:
        defaultZone: "http://localhost:9090/eureka/"

3.cd eurekaserver 
mvn spring-boot:run

4.cd product-service
mvn spring-boot:run

5.cd inventory-service
mvn spring-boot:run

6.cd order-service
mvn spring-boot:run

7.cd gatewayserver
mvn spring-boot:run

8.Acces URLs:
Eureka Dashboard -> http://localhost:9090
Gateway -> http://localhost:8085

