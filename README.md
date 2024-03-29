<h1 align="center">
  Currency Converter
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=github&message=@pdt7&color=8257E5&labelColor=000000" alt="@pdt7" />
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Jaya´s Challenge&color=8257E5&labelColor=000000" alt="Jaya´s Challange" />
</p>

API developed to do currency converter to Jaya´s challenge. The challenge is available at this link [Currency Converter](https://drive.google.com/file/d/1XVrrWv6eTnCarCZf637_Ga_FHZogoRkk/view?usp=drive_link) to Senior Java Developer at Jaya. This currency converter available to 4 currencys (BRL, USD, EUR, JPY)

The project is availabre at this link [Git Hub](https://github.com/pdt7/api-currency-converter).

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Queries with Spring Data JPA
- Dependency inject
- Handling error responses
- Swagger´s Automatic buider with OpenAPI 3

## Como Executar

- Clone git repository
- Build project:
```
$ ./mvnw clean package
```
- Run aplication:
```
$ java -jar target/api-currency-converter.jar
```

A API can be accessed at [localhost:8080](http://localhost:8080).
O Swagger can be accessed at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/):

- Create Transaction 
```
- http POST http://localhost:8080/transaction
[
  {
    "idUsuario": 2,
    "originCurrency": "BRL",
    "originValue": 10,
    "destinationCurrency" : "EUR"
  }
]
```

- Get Transactions
```
- http GET http://localhost:8080/transaction
[
  {
    "descricao": "Desc Todo 1",
    "id": 1,
    "nome": "Todo 1",
    "prioridade": 1,
    "realizado": false
  }
]
```

- Update Transaction
```
$ http PUT http://localhost:8080/transaction
[
  {
    "id": 7,
    "idUsuario": 2,
    "originCurrency": "BRL",
    "originValue": 100,
    "destinationCurrency" : "EUR"
  }
]
```

- Delete Transaction
```
http DELETE http://localhost:8080/transaction/1

[ ]
```