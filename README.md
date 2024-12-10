# library-management-react
 Library Management Web Application with a full-stack incorporation of a 3-tier architecture (React, SpringBoot JDBC, MySQL). This project is made to expedite the task of tracking books, members, and book rentals for easy of use for library members and staff. The goal of this project is to demonstrate real-life applications for database concepts taught in class, as well as to give practical experience with creating a full-stack project. This was made my Lawrence Cuenco and Sungjun An over the course of a 3-week project for CS-157A (Database Management Systems) at San Jose State University.

# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

# Setup

MySQL: https://dev.mysql.com/downloads/installer/

Maven 3.9.9: https://maven.apache.org/download.cgi tutorial: https://phoenixnap.com/kb/install-maven-windows

Java JDK 21: https://www.oracle.com/java/technologies/downloads/ tutorial: https://www.geeksforgeeks.org/download-and-install-java-development-kit-jdk-on-windows-mac-and-linux/

Nodejs : https://nodejs.org/en/download/package-manager

NOTE: MySQL Shell extension in VSCode can be particularly useful for running SQL setup scripts and viewing database information

Ensure you are able to access MySQL from your terminal, and that you can see versions of Maven, Java, and Node with the -version command in your terminal.
Look up tutorials for installation if you are having trouble.

Backend
1. go to /backend/src/main/java/com/lib/library_management_react/resources/, if not included create the application.properties file
2. The application.properties should be in this format utilizing the location of your database server (this project uses Aiven to source its MySQL database)

spring.application.name=library-management-react

spring.datasource.url=jdbc:mysql://{host}:{port}/{database_name}

spring.datasource.username= {username}

spring.datasource.password= {password}

The configuration for this portion in particular can be found in the report paper under "How JDBC is connected to the database". GitHub does not allow me to insert my API keys to the online database for safety purposes, so if accessing from github, take the configuration information from the paper. We use a cloud databse from Aiven to store our data and our backend relies on this file to connect to the database, so it is essential this step is set up properly.

3. Run create_schema.sql and initialize_schema.sql file located in the same folder of the application.properties file

(NOTE: drop_schema.sql is used to clear the entire database, use this to reset if needed)

4. Input "mvn clean install" to set up dependencies for the backend.
5. Start the springboot server with command "mvn spring-boot:run" in your IDE terminal, the default port for this server is localhost:8080

Frontend
1. Ensure you are in the frontend directory in your IDE terminal
2. Input the "npm i" command in the terminal to install dependencies needed to run the application
3. Start the application with "npm run dev" and use the provided link in the terminal in a web browser to access the frontend application.

# Acknowledgements

Saye was used as a source for default book inserts in schema.sql: https://www.sayebrand.com/blogs/stories/25famousbooks

Vondy used to generate landing page image: https://www.vondy.com/