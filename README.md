# Stock Management Application system

## [Click here](https://d-mart-production.up.railway.app/swagger-ui/index.html) to access the Swagger UI.


<hr>
<li>A Sole Project developed by me depicting the implementation of the Stock Management online Manage Stock.
<li>A developement of RESTful API for an Online Stock Management Application system. This API performs all the fundamental CRUD operations .
<br>
 
 # My Roles And Responsibilities
  
<hr>
<br>
<br>

1) Responsible for creating the Stock Item module.

2) Responsible for creating the Store Location Module.

3) Responsible for creating the Store Stocks Module.


<br>
<br>


# Modules
<hr>
<li>Stocks 
<li>Store Location
<li>Store Stocks

<br>

# Features
<hr>
<br>

- Add new stock items to the system.
- Update the quantity of existing stock items.
- Delete stock items from the system.
- Track the movement of stock items between different store locations.
- Provide real-time visibility into the quantity of stock items available at each
store location.

<br>
<br>


<hr>
  


# Teach Stacks Implemented
<hr>
<br>
<br>
<li>Core_Java
<li>Spring framework
<li>Spring Boot Data JPA
<li>Spring Security
<li>Hibernate
<li>Maven
<li>MySQL
<li>Swagger
<li>Lombok

  

<br>
<br>

# Installation & Run
<hr>
<br>
<br>

```
#changing the server port
server.port=8000

#db specific properties

#changing the server port
server.port=8000

#db specific properties
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:stock}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:yourPassword}

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER

logging.level.org.springframework.security=DEBUG

```

<br>
<br>

# API Root Endpoint
<hr>
<br>
<br>

```
http://localhost:8000/
```

```
http://localhost:8000/swagger-ui/#
```

```
https://d-mart-production.up.railway.app/swagger-ui/index.html#/
```










