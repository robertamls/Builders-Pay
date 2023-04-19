<h1> Builders-Pay 🧾 </h1>

### Api developed for Builders technical challenge.

## Stack 📌
+ JDK 17
+ Mongo, mongo compass;
+ Lombok;
+ Dockerif you prefer.

If you prefer to use docker or an IDE, in my case I used intellij.

## Preparing the environment 🖥️

+ Create database "builderspay"

Por padrão, o lombok já vem nos plugins do maven, mas caso seja necessário uma instalação aqui está o link para obter o jar.
<p><a href="https://projectlombok.org/downloads/lombok.jar">Lombok</a></p>


## Commands ✅

First you perform a 
```mvn clean"```
and then a 
```mvn install```
to run through the IDE, just run the BuilderPayApplication.class, but to run with docker perform
```docker build -t "builderspay"``` 
and then a 
```docker-compose up -d```
