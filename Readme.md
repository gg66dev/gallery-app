
Description
================

* TODO


Stack
==========

* Spring-mvc 5 (Java 11) / tomcat 9
* AngularJS 1.6
* PostgreSQL 10

Observation
===================

* about problem when up postgres

```
FATAL:  data directory "/var/lib/postgresql/data" has wrong ownership
```

* just run the next command

```
docker volume create postgres_database
```
