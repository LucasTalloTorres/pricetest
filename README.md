# PriceTest - Spring boot project
A hexagonal architecture project with Spring boot 3


#### Getting Started
This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.


#### Usage
###### mvn with PostgreSQL database

Go to docker directory and run docker-compose to make PostgreSQL server up
  ```sh
  docker-compose up -d
  ```

  ```sh
  mvn clean install
  
  mvn spring-boot:run
  ```


###### mvn with H2 database
If we don't want to use Docker you can run it with h2 database

  ```sh
  mvn clean install
  
  mvn spring-boot:run -Dspring-boot.run.profiles=h2
  ```

#### Data examples
This is some data to fill de database:
  ```sql
  INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
  VALUES
      (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
      (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
      (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
      (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
  ```


#### Curl

To do requests to the server
  ```sh
  curl "http://localhost:8080/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1"
  ```


#### Contact

Lucas Tallo Torres - lucas.tallo.torres@gmail.com

Project Link: [https://github.com/LucasTalloTorres/pricetest](https://github.com/LucasTalloTorres/pricetest)
