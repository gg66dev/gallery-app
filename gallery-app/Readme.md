## development database

* to create volumen for development database

```
docker volume create postgres_database
```


## Unit testing - Repositories

* build docker of testing database

```
docker build -f Dockerfile.db_test -t postgres_test .
```

* run container

```
docker run -d -t -i -e POSTGRES_PASSWORD='postgres_test' \
-e POSTGRES_USER='postgres_test' \
-e POSTGRES_DB='postgres_test' \
-p 5432:5432 \
--name postgres_test postgres_test
```

* help 1
    * logs
    
```
docker logs -tf <container_id>
```

* help 2    
    * ip docker machine

```
docker-machine ip
```

* run tests
    * require JDK 11

```
mvn compile
mvn test
```