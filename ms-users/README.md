# ms-users
User microservices.
This microservice is responsible for managing the user of the application.
It is responsible for creating, updating and deleting the user.


## How to run
- Run the database management microservice. [Readme](../ms-database/README.md)
- Check if the database is created.
- Configure the application.yml file with the database connection information.
- To run the microservice, you need to run the spring boot application.


## How to use
- By default, the port is 8002, but you can change it in the application.yml file.
- This is the endpoint:
    * '/api/v1/users/': Get all users.
    * '/api/v1/users/{id}': Get user by id
    * '/api/v1/users/username': Get user by username

- Also, you can see the definition of the endpoint in local swagger '/swagger-ui/index.html'.

### Pre-requisites
- Run the database management microservice. [Readme](../ms-database/README.md)
- Api definition exists in the api module. [Readme](../ms-api/README.md)
