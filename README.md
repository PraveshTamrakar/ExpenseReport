API Guidelines with error messages:-

https://docs.google.com/document/d/1LuCAUWcAkL3M-N_Ci13HBFNC4bGEeHkNJP6F9Iq0qyU/edit?usp=sharing


Steps to Setup the project

1. Clone the application

   git clone https://github.com/PraveshTamrakar/ExpenseReport.git


2. Create Mysql database

   create table expense
   
   
   CREATE TABLE `expense` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expenseReportName` varchar(45) DEFAULT NULL,
  `expenseName` varchar(45) DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  `CreatedDate` date NOT NULL,
  `reporterName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


3. Change mysql username and password as per your installation

   open src/main/resources/application.properties

   change spring.datasource.username and spring.datasource.password as per your mysql installation
   also check the url; with the name of schema. In this example schema name is expense_management.

4. Build and run the app using maven


   mvn spring-boot:run


   The app will start running at http://localhost:8080.

==============================================================================================================================================================

Explore Rest APIs

The app defines following CRUD APIs.

GET /api/expense

POST /api/expense

GET /api/expense/{id}

PUT /api/expense/{id}

DELETE /api/expense/{id}


You can test them using postman or any other rest client.
