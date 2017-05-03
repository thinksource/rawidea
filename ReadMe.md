# ReadMe

## Before Running

### 1, Intstall a database 
Install a database (I use mariadb which is the same as mysql) and then change database connection file.


File: src/main/resources/application.properties

change the line:

```
spring.datasource.url=You should special database by your local setting
```

## How to Run

### 1, Intstall a database 
Install a database (I use mariadb which is the same as mysql) and then change database connection configurarion in 

```
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

  
## 3,Eclipse

Since I develop the code by Eclipse, you can also import the project by eclipse import.

if you use Eclipse, please project-> Maven -> Update Project firstly. 

The RawideaApplication is main Springboot application, you should run it.

## 4, Conditions
### 1, if more than 3 games will just get first 3 game and neglect the rest of them. 
### 2, if only have one person get scores, then only one person get name, other personal name is null
### 3, if the name of get score with more than 3 persons, it will return 500 internal error

## 5, desgin consideration:
### 1, I divided the two object one is BadmintonMatch, other is Game, and use one-to-many relationship between them. The Scorelist and Score class is for receiving 

### 2，since not all column fileds need to list on response json, I use ApiResponse object to deal with 


## 6， Todo
### 1， Do not finished configuration the @AutoconfigureTestDatabase so you should configurate your test database by yourself and then thee RepositoryTest should be working
