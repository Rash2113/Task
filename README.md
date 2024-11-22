1.How did you run the code?
  I ran the Spring Boot application using Maven by executing the mvn spring-boot:run command. The application is set up to connect to a PostgreSQL database with the connection URL configured in application.properties.
 1After running the application, I accessed the endpoints using Postman (e.g., http://localhost:8080/api/categories) to verify the API functionality.

2.How did you run the machine test?
  I ran the machine test using Postman to validate the CRUD operations. I tested the POST request for creating new categories and products, GET request to fetch the details, PUT to update records, and DELETE to remove them. 
  After each test, I verified the response status and checked the database to confirm that the changes were applied.

3.DB Design
  The database design includes two main entities: Category and Product. A Category can have multiple Products, forming a One-to-Many relationship. 
  The Product table has a foreign key category_id that references the Category table’s primary key id. Below is the Entity-Relationship diagram showing the structure of the two tables.
