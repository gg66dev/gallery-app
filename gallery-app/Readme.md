## development database

* to create volumen for development database

```
docker volume create postgres_database
```

* build docker of devel database

```
docker build -f Dockerfile.db -t db_devel .
```

* run container

```
docker run -d -t -i -e POSTGRES_PASSWORD='gallery-app' \
-e POSTGRES_USER='gallery-app' \
-e POSTGRES_DB='gallery-app' \
-p 5432:5432 \
--volume source=postgres_database,destination=/var/lib/postgresql/data \
--name db_devel db_devel 
```

## Unit testing - Repositories

* build docker of testing database

```
docker build -f Dockerfile.db -t postgres_test .
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