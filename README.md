Steps to Setup

1. Clone the application

git clone https://github.com/PraveshTamrakar/ExpenseReport.git


2. Create Mysql database
   create database expense

3. Change mysql username and password as per your installation

open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your mysql installation
also check the url; with the name of schema. In this example schema name is expense_management.

4. Build and run the app using maven
mvn spring-boot:run
The app will start running at http://localhost:8080.

Explore Rest APIs

The app defines following CRUD APIs.

GET /api/expense

POST /api/expense

GET /api/expense/{id}

PUT /api/expense/{id}

DELETE /api/expense/{id}
You can test them using postman or any other rest client.
