# ReadMe

## How to Run

### 1, Intstall a database 
Install a database (I use mariadb which is the same as mysql) and then change database connection file in

I using maven build application and I already install spring-boot -plugin for mvn you can simple run it by command line:

```
mvn spring-boot:run
```

## 2, the running protal 

For the function with receiving post scores json string, the protal is:

```
http://localhost:8080/scorelist
```

The search badminton match list is：

```
http://localhost:8080/fetch?matchid=0
```

The version confirm running on

```
http://localhost:8080/version
```

This service is just check spring boot running or not

  


## 3, Conditions
### 1, if more than 3 games will just get first 3 game and neglect the rest of them. 
### 2, if only have one person get scores, then only one person get name, other personal name is null
### 3, if the name of get score with more than 3 persons, it will return 500 internal error

## 4, desgin consideration:
### 1, I divided the 
