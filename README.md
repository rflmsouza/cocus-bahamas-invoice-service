# cocus-bahamas-invoice-service

This project use a postgresql database.

## Instalation

Package the application first.

```bash
./mvnw clean package -DskipTests
```

OBS: It's necessary to skip the tests here because the application.yml is configured to locate the database container.



Run the service using docker-compose from project's root directory:

```bash
docker-compose up
```


Or, if you prefer to run to project manually, don't forget to change the application.yml to point to a localhost database.

```bash
docker run -d -p 5432:5432 --name builders-postgres -e POSTGRES_PASSWORD=pwdForTheTest -e POSTGRES_DB=bahamas postgres
```

then

```bash
./mvnw spring-boot:run
```