# app-management
Database management microservices.
This microservice is responsible for managing the database of the application. 
It is responsible for creating, updating and deleting the database.
For the management of the database, the microservice uses the [Flyway](https://flywaydb.org/) tool.

## How to run
- Install the database, we use mysql, but you can use any database that is supported by flyway.
- Create a database for the application. You can use docker compose, por example [My docker compose](https://github.com/ANGELVBR/my-core/blob/main/myApp/docker/docker-compose.yml)
- Configure the application.yml file with the database connection information.
- To run the microservice, you need to run the spring boot application.

## How to use
- By default, the port is 8001, but you can change it in the application.yml file.
- Put the database scripts in the folder `src/main/resources/db/migration`. The scripts must be in the format `V1__name.sql`.
- To get the version scripts, exists the following endpoint:
    * '/api/v1/histories': Get all versions of the scripts.
    * '/api/v1//histories/{id}': Get details of a version of the scripts.
  
- Also, you can see the definition of the endpoint in local swagger '/swagger-ui/index.html'.







 
