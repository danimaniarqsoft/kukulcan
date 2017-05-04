# kukulcan

## Project description

**Kukulkan** is an Abstract Generator for different platforms and technologies, it is the creator of the base software architecture.

## Some history

Kukulcan is one of the three gods that was thought to have created the Earth. He is a serpent in his natural form and was responsible for teaching the Mayan's about such things as how to run a civilization, agriculture, and medicine.

## Running the App

The app is under development so that you need to run the next test class: GenerationServiceTest

# References

### Spring Boot
For spring boot exception handling:
http://www.baeldung.com/exception-handling-for-rest-with-spring

### Spring Reference
https://github.com/eugenp/tutorials

### JPA - Spring Data
#### Official Documentation **spring-data:**
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

#### JPA - General Documentation
https://en.wikibooks.org/wiki/Java_Persistence

### Spring Security
#### Official Documentation
http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/html5/

#### CSRF Attacks
https://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/

## H2 Database
#### Documentación oficial
http://www.h2database.com/

#### Spring Security and H2 Console
https://dzone.com/articles/using-the-h2-database-console-in-spring-boot-with

#### Install Oracle Jar

```bash
mvn install:install-file -Dfile=./ojdbc6-11.2.0.3.jar -DgroupId=oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar
```


## Docker Kukulkan

#### Building the docker image:

```
mvn package docker:build -Dmaven.test.skip=true
```

#### Creating the docker compose file


```yml
mongodb:
  image: mongo:3.2
  hostname: mongodb
  container_name: mongodb
  expose:
      - "27017"
  command: --nojournal
kukulkan:
  image: danimaniarqsoft/kukulkan
  hostname: kukulkan
  container_name: kukulkan
  links:
    - mongodb
  ports:
    - "8080:8080"
  command: -dbhost mongodb
```

running the docker compose file

```bash
docker-compose up
```
