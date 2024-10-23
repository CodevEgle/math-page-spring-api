# Project: Mathematics Learning Page

This project is a web application for learning mathematics, consisting of a Spring backend and a React frontend. The application uses a MySQL database for storing data.

## Project Description

The Mathematics Learning Page aims to provide an interactive platform where users can learn and practice mathematics. The backend is built with Spring Boot, which handles the API and business logic, while the frontend is developed using React for a dynamic user interface. Data is stored in a MySQL database.

## Prerequisites

Make sure you have the following dependencies installed on your system:
- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js and npm](https://nodejs.org/)
- [MySQL](https://dev.mysql.com/downloads/)

## Getting Started

1. **Setting Up the MySQL Database**

   - Install MySQL if it is not already installed on your system. Follow the instructions on the [MySQL download page](https://dev.mysql.com/downloads/).
   - Create a new database for the project:
     - Open a MySQL client (e.g., MySQL Workbench, command-line client).
     - Run the following command to create a database:
       ```sql
       CREATE DATABASE matematika;
       ```
   - Configure the database connection in the `application.properties` or `application.yml` file located in the `backend/src/main/resources` directory.

     Example `application.properties` configuration:
     ```properties
     # Database
     spring.datasource.url=jdbc:mysql://localhost:3306/matematika
     spring.datasource.username=root
     spring.datasource.password=code
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

     # JPA/Hibernate
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.open-in-view=true

     # Logging
     logging.level.org.hibernate.SQL=DEBUG
     logging.level.lt.ca.javau10.controllers=DEBUG
     ```

2. **Running the Backend (Spring)**

   - Open a terminal window.
     - **Windows**: Use Command Prompt (Cmd) or PowerShell.
     - **Mac/Linux**: Use the built-in Terminal application.
   - Navigate to the `backend` directory:
     ```bash
     cd 56-matematikosMokymosi
     ```
   - Start the Spring application using Maven:
     ```bash
     mvn spring-boot:run
     ```
   - If the startup is successful, the backend will be running at http://localhost:8080.

   - **Alternative: Running from an IDE**
     - Open the project in an IDE such as IntelliJ IDEA or Eclipse.
     - Locate the main Spring Boot class with the `@SpringBootApplication` annotation.
     - Run it as a Java Spring Boot application.

3. **Running the Frontend (React)**

   - Open a new terminal window or tab.
   - Navigate to the `math-app` directory:
     ```bash
     cd math-app
     ```
   - Install all required dependencies:
     ```bash
     npm install
     ```
   - Start the React application:
     ```bash
     npm start
     ```
   - If the startup is successful, the frontend will be running at [http://localhost:3000](http://localhost:3000).

4. **Folder Structure**

   - `56-MatematikosMokymosi/`: Contains the Spring Boot application, including source code and configuration files.
   - `math-app/`: Contains the React application, including all components, services, and configuration files.

5. **Technologies Used**

   - **Spring Boot** - Backend framework for building Java applications.
   - **React** - JavaScript library for building user interfaces.
   - **MySQL** - Relational database for data storage.
   - **Maven** - Dependency management and build automation tool.
   - **Node.js & npm** - JavaScript runtime and package manager for the frontend.

6. **Environment Variables Configuration**

   - To avoid hardcoding sensitive information like database credentials, you can use environment variables. For example, you can set the database properties as environment variables and use them in `application.properties` like this:
     ```properties
     spring.datasource.url=${DB_URL}
     spring.datasource.username=${DB_USERNAME}
     spring.datasource.password=${DB_PASSWORD}
     ```

7. **Building the Project**

   - To create a production build:
     - **Backend**: Run `mvn clean package` in the `backend` directory.
     - **Frontend**: Run `npm run build` in the `math-app` directory. This will generate a `build` folder containing the optimized static files.

8. **Testing**

   - To run tests:
     - **Backend tests**: Execute `mvn test` in the `backend` directory.

9. **Troubleshooting**

   - **Database Connection Issues**: Ensure the MySQL server is running, and the credentials in the `application.properties` file match the MySQL user and database.
   - **Port Conflicts**: Make sure ports 8080 and 3000 are not being used by other applications.

10. **Common Issues and Solutions**

   - **Unable to connect to MySQL**: Check if the database server is running and the connection parameters are correct.
   - **Build errors**: Make sure all dependencies are installed, and there are no conflicts in the configurations.

